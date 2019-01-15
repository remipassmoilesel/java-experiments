package org.remipassmoilesel.javafxexperiments.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class EventUtil implements ApplicationListener<Event> {

    @Autowired
    protected ApplicationEventPublisher<Event> publisher;

    @Override
    void onApplicationEvent(Event event) {

    }

}
