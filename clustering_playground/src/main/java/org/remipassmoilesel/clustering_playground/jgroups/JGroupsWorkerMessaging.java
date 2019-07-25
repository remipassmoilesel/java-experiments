package org.remipassmoilesel.clustering_playground.jgroups;

import lombok.extern.slf4j.Slf4j;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JGroupsWorkerMessaging extends ReceiverAdapter {

    private JGroupsCoordinator coordinator;

    @Autowired
    public JGroupsWorkerMessaging(JGroupsCoordinator coordinator) {
        this.coordinator = coordinator;
        coordinator.onMessage(this);
    }

    @Override
    public void receive(Message message) {
        if (!coordinator.isMaster()) {
            log.info(String.format("Message received: %s", new String(message.getBuffer())));
        }
    }

}
