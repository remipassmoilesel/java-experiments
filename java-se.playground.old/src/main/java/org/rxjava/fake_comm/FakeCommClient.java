package org.rxjava.fake_comm;

import java.util.HashMap;

public class FakeCommClient {

    private final HashMap<String, SyncHandler> handlers;

    public FakeCommClient() {
        this.handlers = new HashMap<>();
    }

    public Message request(String subject, Message message) {
        return this.handlers.get(subject).handleMessage(subject, message);
    }

    public void subscribe(String subject, SyncHandler handler) {
        this.handlers.put(subject, handler);
    }

}
