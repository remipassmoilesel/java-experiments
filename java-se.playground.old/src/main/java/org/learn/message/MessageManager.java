package org.learn.message;

import java.util.ArrayList;

public abstract class MessageManager {

	public final static String RECEIVED = "RECEIVED";
	public final static String ERROR = "ERROR";

	/** Les destinataires des messages */
	protected ArrayList<MessageManager> destinations;
	protected String managerID = this.getClass().getName();

	public MessageManager() {
		this.destinations = new ArrayList<MessageManager>();
	}

	public abstract String messageIncoming(Message message);

	public ArrayList<MessageManager> getDestinations() {
		return destinations;
	}

	public void addDestinations(MessageManager dest) {
		destinations.add(dest);
	}

	public void sendMessage(String text) {
		Message msg = newMessage(text);
		for (MessageManager d : destinations) {
			d.messageIncoming(msg);
		}
	}

	public void sendMessage(Message message) {
		for (MessageManager d : destinations) {
			d.messageIncoming(message);
		}
	}

	public void sendMessage(Message message, long delay) {
		if (delay != -1) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sendMessage(message);
	}

	public Message newMessage(String text) {
		return new Message(managerID, text);
	}
	
	public String getManagerID() {
		return managerID;
	}

}
