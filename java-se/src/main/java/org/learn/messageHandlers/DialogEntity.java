package org.learn.messageHandlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.Utils;
import org.learn.Mood;

public class DialogEntity implements Serializable, Comparable<DialogEntity> {

	private static final long serialVersionUID = 3232903696027349480L;

	private ArrayList<String> keywords;
	private String response;
	private int mood;

	private int coeff;

	public DialogEntity() {
		this.keywords = new ArrayList<String>();
		this.response = new String();
		this.mood = Mood.NORMAL;
	}

	public int getCoeff() {
		return coeff;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public String getResponse() {
		return response;
	}

	public int getMood() {
		return mood;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	@Override
	public String toString() {
		String output = "[" + this.getClass().getSimpleName() + ": "
				+ Utils.join(",", Arrays.asList(coeff, mood, response, keywords))
				+ "]";
		return output;
	}

	@Override
	public int compareTo(DialogEntity de) {
		if (de.coeff == coeff)
			return 0;
		else
			return de.coeff < coeff ? -1 : +1;
	}

}
