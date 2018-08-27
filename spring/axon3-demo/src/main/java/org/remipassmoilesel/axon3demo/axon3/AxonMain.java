package org.remipassmoilesel.axon3demo.axon3;

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

    // See: https://www.baeldung.com/axon-cqrs-event-sourcing

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

        // Register the aggregate, and let it handle commands to produce events
        AggregateAnnotationCommandHandler<MessagesAggregate> handler = new AggregateAnnotationCommandHandler<>(
                MessagesAggregate.class, repository);
        handler.subscribe(commandBus);

        // register message event handler
        AnnotationEventListenerAdapter annotationEventListenerAdapter = new AnnotationEventListenerAdapter(new MessageEventHandler());
        AnnotationEventListenerAdapter annotationEventListenerAdapter2 = new AnnotationEventListenerAdapter(new MessageEventHandler2());
        eventStore.subscribe(eventMessages -> eventMessages.forEach(e -> {
            try {
                annotationEventListenerAdapter.handle(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                annotationEventListenerAdapter2.handle(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }));

        return commandGateway;
    }

}
