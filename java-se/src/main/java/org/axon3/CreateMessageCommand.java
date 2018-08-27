package org.axon3;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateMessageCommand {

    @TargetAggregateIdentifier
    private String id;
    private String text;

    public CreateMessageCommand(String id, String text) {
        System.out.println("CreateMessageCommand - Command CreateMessageCommand created");
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
