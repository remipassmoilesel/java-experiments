package org.remipassmoilesel.axon3demo.axon3;

import java.util.logging.Logger;

public class MessageCreatedEvent {

    private static final Logger logger = Logger.getLogger(MessageCreatedEvent.class.getName());

    private String id;
    private String text;

    public MessageCreatedEvent(String id, String text) {
        logger.severe("Event MessageCreatedEvent created: " + id);
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
