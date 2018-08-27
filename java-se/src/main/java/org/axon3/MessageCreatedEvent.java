package org.axon3;

public class MessageCreatedEvent {

    private String id;
    private String text;

    public MessageCreatedEvent(String id, String text) {
        System.out.println("MessageCreatedEvent - Event MessageCreatedEvent created: " + id);
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
