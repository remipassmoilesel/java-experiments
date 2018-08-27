package org.remipassmoilesel.axon3demo.axon3;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.logging.Logger;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class MessagesAggregate {

    private static final Logger logger = Logger.getLogger(MessagesAggregate.class.getName());

    @AggregateIdentifier
    private String id;

    public MessagesAggregate() {
        logger.severe("MessagesAggregate created");
    }

    @CommandHandler
    public MessagesAggregate(CreateMessageCommand command) {
        logger.severe("MessagesAggregate created with command: " + command.getId());
        apply(new MessageCreatedEvent(command.getId(), command.getText()));
    }

    @EventHandler
    public void on(MessageCreatedEvent event) {
        logger.severe("MessagesAggregate -  " + event.getId());
        this.id = event.getId();
    }

}