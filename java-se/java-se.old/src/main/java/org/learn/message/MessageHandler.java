package org.learn.message;


public abstract class MessageHandler {

	public abstract HandleResponse handle(MessageManager dests, Message msg);

	public abstract HandleResponse acceptMessage(MessageManager dests,
			Message message);

}
