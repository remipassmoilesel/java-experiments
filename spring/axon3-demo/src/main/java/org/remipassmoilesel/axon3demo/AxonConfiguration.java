package org.remipassmoilesel.axon3demo;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class AxonConfiguration {

    private static final Logger logger = Logger.getLogger(AxonConfiguration.class.getName());

    @Bean
    public EventStore eventStore() {
        logger.severe("Instantiating InMemoryEventStorageEngine");
        return new EmbeddedEventStore(new InMemoryEventStorageEngine());
    }

    @Bean
    public CommandBus commandBus() {
        logger.severe("Instantiating SimpleCommandBus");
        return new SimpleCommandBus();
    }

    @Bean
    public EventBus eventBus() {
        logger.severe("Instantiating EventBus");
        return new SimpleEventBus();
    }
}
