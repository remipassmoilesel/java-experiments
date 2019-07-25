package org.remipassmoilesel.clustering_playground.jgroups;

import io.vavr.collection.Stream;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jgroups.JChannel;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@Component
public class JGroupsCoordinator extends ReceiverAdapter {

    private JChannel channel;

    @PostConstruct
    public JChannel setup() throws Exception {
        this.channel = new JChannel("src/main/resources/jgroups/udp.xml");
        channel.setDiscardOwnMessages(true);
        channel.setReceiver(this);
        channel.setName("cluster-playground:cluster-name:node-" + LocalDateTime.now());
        channel.connect("cluster-playground:cluster-name");
        return channel;
    }

    @Override
    public void viewAccepted(View clusterView) {
        log.info(String.format("New cluster view received: %s", clusterView));
        masterElection(clusterView);
    }

    private void masterElection(View clusterView) {
        val nodePosition = Stream.ofAll(clusterView.getMembers()).sortBy(addr -> addr.toString()).indexOf(this.channel.getAddress());
        if (nodePosition == 0) {
            log.info("I am the master, I send orders !");
        } else {
            log.info("I am a worker, I love orders !");
        }
    }
}
