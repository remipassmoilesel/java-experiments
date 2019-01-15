package org.axon3;

import org.axonframework.eventhandling.EventHandler;

public class MessageEventHandler2 {

    @EventHandler
    public void handle(MessageCreatedEvent event) {
        System.out.println("MessageEventHandler2 - Handling event " + event.getId() + " " + event.getText());
    }

}
