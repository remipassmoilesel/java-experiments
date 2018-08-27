package org.axon3;

import org.axonframework.commandhandling.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import java.util.UUID;

public class AxonMain {

    public static void main(String[] args) {

        AxonMain axonMain = new AxonMain();
        CommandGateway commandGateway = axonMain.setupAxonBus();

        String itemId = UUID.randomUUID().toString();
        commandGateway.send(new CreateMessageCommand(itemId, "Hello, how is your day?"));
    }

    public CommandGateway setupAxonBus() {

        CommandBus commandBus = new SimpleCommandBus();
        CommandGateway commandGateway = new DefaultCommandGateway(commandBus);

        EventStore eventStore = new EmbeddedEventStore(new InMemoryEventStorageEngine());

        EventSourcingRepository<MessagesAggregate> repository = new EventSourcingRepository<>(MessagesAggregate.class, eventStore);

        AggregateAnnotationCommandHandler<MessagesAggregate> handler = new AggregateAnnotationCommandHandler<>(
                MessagesAggregate.class, repository);
        handler.subscribe(commandBus);

        AnnotationEventListenerAdapter annotationEventListenerAdapter = new AnnotationEventListenerAdapter(new MessageEventHandler());
        eventStore.subscribe(eventMessages -> eventMessages.forEach(e -> {
            try {
                annotationEventListenerAdapter.handle(e);
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }));

        return commandGateway;
    }

}
