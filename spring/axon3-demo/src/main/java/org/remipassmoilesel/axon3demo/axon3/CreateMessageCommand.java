package org.remipassmoilesel.axon3demo.axon3;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.logging.Logger;

public class CreateMessageCommand {

    private static final Logger logger = Logger.getLogger(CreateMessageCommand.class.getName());

    @TargetAggregateIdentifier
    private String id;
    private String text;

    public CreateMessageCommand(String id, String text) {
        logger.severe("Command CreateMessageCommand created");
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
