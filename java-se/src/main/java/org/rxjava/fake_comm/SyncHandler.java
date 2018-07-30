package org.rxjava.fake_comm;

public interface SyncHandler {
    public Message handleMessage(String subject, Message message);
}
