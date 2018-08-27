package org.remipassmoilesel.axon3demo;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.remipassmoilesel.axon3demo.axon3.MessageEventHandler;
import org.remipassmoilesel.axon3demo.axon3.MessageEventHandler2;
import org.remipassmoilesel.axon3demo.axon3.MessagesAggregate;
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
    public CommandGateway commandGateway(CommandBus commandBus) {
        logger.severe("Instantiating CommandGateway");
        return new DefaultCommandGateway(commandBus);
    }

    @Bean
    public EventBus eventBus() {
        logger.severe("Instantiating EventBus");
        return new SimpleEventBus();
    }

    // This configuration should not be mandatory
    @Bean
    public org.axonframework.config.Configuration configuration(EventStore eventStore) {
        logger.severe("Configuring axon");

        EventHandlingConfiguration ehConfiguration = new EventHandlingConfiguration()
                .registerEventHandler(conf -> new MessageEventHandler())
                .registerEventHandler(conf -> new MessageEventHandler2());

        org.axonframework.config.Configuration configuration = DefaultConfigurer.defaultConfiguration()
                .configureEventStore(conf -> eventStore)
                .configureAggregate(MessagesAggregate.class)
                .registerModule(ehConfiguration)
                .buildConfiguration();

        configuration.start();
        return configuration;
    }

}
