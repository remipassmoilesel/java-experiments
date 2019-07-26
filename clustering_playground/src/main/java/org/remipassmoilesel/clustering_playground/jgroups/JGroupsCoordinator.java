package org.remipassmoilesel.clustering_playground.jgroups;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jgroups.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;

import static io.vavr.API.unchecked;

// TODO: test an RPC call with Message Dispatcher

@Component
@Slf4j
@Getter
@Setter
public class JGroupsCoordinator {

    private String clusterName = "cluster-playground:cluster-name";
    private String nodeName = String.format("%s:node-%s", clusterName, LocalDateTime.now());
    private boolean master = false;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private JChannel channel;
    private CoordinatorReceiver receiver;

    @PostConstruct
    public void setup() throws Exception {
        setupChannel();
    }

    @PreDestroy
    public void down() {
        this.channel.close();
    }

    private void setupChannel() throws Exception {
        this.receiver = new CoordinatorReceiver();
        channel = new JChannel("src/main/resources/jgroups/udp.xml")
                .setDiscardOwnMessages(true)
                .setReceiver(receiver)
                .setName(nodeName);

        channel.connect(clusterName);
    }

    private void masterElection(View clusterView) {
        val nodePosition = Stream.ofAll(clusterView.getMembers()).sortBy(addr -> addr.toString()).indexOf(this.channel.getAddress());
        if (nodePosition == 0) {
            log.info("I am the master, I send orders !");
            this.master = true;
        } else {
            log.info("I am a worker, I love orders !");
            this.master = false;
        }
    }

    public void onMessage(MessageListener listener) {
        this.receiver.addListener(listener);
    }

    public void broadcastMessage(byte[] bytes) {
        val message = new Message(null, bytes);
        unchecked(() -> channel.send(message)).apply();
    }

    private class CoordinatorReceiver extends ReceiverAdapter {

        private List<MessageListener> messageListeners = List.empty();

        @Override
        public void viewAccepted(View clusterView) {
            log.info(String.format("New cluster view received: %s", clusterView));
            masterElection(clusterView);
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

        public void addListener(MessageListener listener) {
            this.messageListeners = this.messageListeners.append(listener);
        }
    }

}
