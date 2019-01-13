package org.axon3;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class MessagesAggregate {

    @AggregateIdentifier
    private String id;

    public MessagesAggregate() {
        System.out.println("MessagesAggregate - MessagesAggregate created");
    }

    @CommandHandler
    public MessagesAggregate(CreateMessageCommand command) {
        System.out.println("MessagesAggregate - MessagesAggregate created with command: " + command.getId());
        apply(new MessageCreatedEvent(command.getId(), command.getText()));
    }

    @EventHandler
    public void on(MessageCreatedEvent event) {
        System.out.println("MessagesAggregate - Event received: " + event.getId());
        this.id = event.getId();
    }

}