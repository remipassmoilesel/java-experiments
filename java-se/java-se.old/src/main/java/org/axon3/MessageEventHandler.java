package org.axon3;

import org.axonframework.eventhandling.EventHandler;

public class MessageEventHandler {

    @EventHandler
    public void handle(MessageCreatedEvent event) {
        System.out.println("MessageEventHandler - Handling event " + event.getId() + " " + event.getText());
    }

}
