package jgroups.k8s.cluster;

import io.vavr.collection.Stream;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jgroups.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

import static io.vavr.API.unchecked;

@Slf4j
@Component
@Profile("!test")
public class JGroupsCoordinator {

    enum NodeRole {
        MASTER, WORKER
    }

    private final static String CONFIG_DEV = "/jgroups/udp.xml";
    private final static String CONFIG_PROD = "/jgroups/dns.xml";

    private NodeRole state = NodeRole.WORKER;
    private JChannel channel;
    private CopyOnWriteArrayList<ClusterStateListener> listeners = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<MessageListener> messageListeners = new CopyOnWriteArrayList<>();

    @PostConstruct
    public JChannel setup() throws Exception {
        this.channel = new JChannel(this.getClass().getResourceAsStream(CONFIG_DEV));
        channel.setDiscardOwnMessages(true);
        channel.setReceiver(new ReceiverAdapter(){

            @Override
            public void viewAccepted(View clusterView) {
                synchronized (JGroupsCoordinator.this.state) {
                    JGroupsCoordinator.this.state = getNodeRole(clusterView);
                }
                notifyListeners(JGroupsCoordinator.this.state);
            }

            @Override
            public void receive(Message message) {
                messageListeners.forEach(listener -> {
                    try {
                        listener.receive(message);
                    } catch (Exception err) {
                        log.error(err.getMessage(), err);
                    }
                });
            }
        });
        channel.setName("local-cluster:node-" + LocalDateTime.now());
        channel.connect("local-cluster");
        return channel;
    }

    public void broadcastMessage(byte[] bytes) {
        val message = new Message(null, bytes);
        unchecked(() -> channel.send(message)).apply();
    }

    public void addClusterStateListener(ClusterStateListener listener) {
        this.listeners.add(listener);
        this.notifyListeners(this.state);
    }

    private void notifyListeners(NodeRole role) {
        if (NodeRole.MASTER == role) {
            listeners.forEach(listener -> listener.onBecameMaster());
        } else {
            listeners.forEach(listener -> listener.onBecameWorker());
        }
    }

    private NodeRole getNodeRole(View clusterView) {
        val isMaster = Stream.ofAll(clusterView.getMembers())
                .sortBy(addr -> addr.toString())
                .indexOf(this.channel.getAddress()) == 0;
        return isMaster ? NodeRole.MASTER : NodeRole.WORKER;
    }

}
