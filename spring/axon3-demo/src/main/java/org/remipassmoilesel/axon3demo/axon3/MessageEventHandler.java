package org.remipassmoilesel.axon3demo.axon3;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MessageEventHandler {

    private static final Logger logger = Logger.getLogger(MessageEventHandler.class.getName());

    public MessageEventHandler() {
        logger.severe("MessageEventHandler instancied");
    }

    @EventHandler
    public void handle(MessageCreatedEvent event) {
        logger.severe("Handling event " + event.getId() + " " + event.getText());
    }

}
