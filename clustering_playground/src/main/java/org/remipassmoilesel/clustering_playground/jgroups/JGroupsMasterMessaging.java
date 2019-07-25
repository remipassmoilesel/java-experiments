package org.remipassmoilesel.clustering_playground.jgroups;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JGroupsMasterMessaging {

    private JGroupsCoordinator coordinator;

    @Autowired
    public JGroupsMasterMessaging(JGroupsCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendMessages() {
        if (coordinator.isMaster()) {
            val message = "Hey, everybody at work NOW !";
            log.info(String.format("Sending: %s", message));
            coordinator.broadcastMessage(message.getBytes());
        }
    }

}
