package org.learn.message;


public abstract class ServiceHandler {

	public abstract HandleResponse handle(MessageManager to,
			ServiceMessage message);

	public abstract HandleResponse acceptMessage(MessageManager to,
			ServiceMessage message);

}
