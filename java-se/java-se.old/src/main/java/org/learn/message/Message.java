package org.learn.message;

public class Message {

	private String text;
	private String from;

	public Message(String from, String text) {
		this.from = from;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String message) {
		this.text = message;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
