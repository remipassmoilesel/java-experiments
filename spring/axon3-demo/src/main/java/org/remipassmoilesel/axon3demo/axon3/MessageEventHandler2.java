package org.remipassmoilesel.axon3demo.axon3;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MessageEventHandler2 {

    private static final Logger logger = Logger.getLogger(MessageEventHandler2.class.getName());

    public MessageEventHandler2() {
        logger.severe("MessageEventHandler2 instancied");
    }

    @EventHandler
    public void handle(MessageCreatedEvent event) {
        logger.severe("Handling event " + event.getId() + " " + event.getText());
    }

}
