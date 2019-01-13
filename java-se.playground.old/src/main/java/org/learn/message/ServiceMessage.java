package org.learn.message;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Message de service. Un message de service est formatté et permet de modifier
 * le comportement de Learn.
 * <p>
 * Les messages de services respectent ce format:
 * //message_id[;arg[=value];arg...]
 * 
 * @author remipassmoilesel
 *
 */
public class ServiceMessage {

	/** L'identifiant de message, en tout début de message */
	private static final String IDENTIFIER = "/";

	/** Le séparateur de membres du message */
	private static final String SEP = ";";

	/** L'identifiant du message */
	private String messageID;

	/** Les arguments, optionnels, du message */
	private HashMap<String, String> args;

	private ServiceMessage(String message) {
		this.messageID = message.toLowerCase();
		this.args = new HashMap<String, String>();
	}

	public String getMessageID() {
		return messageID;
	}

	public HashMap<String, String> getArgs() {
		return args;
	}

	public void setArg(String arg, String val) {
		args.put(arg, val);
	}

	@Override
	public String toString() {
		String output = "[" + ServiceMessage.class.getSimpleName() + ": "
				+ messageID + ", args: " + args.toString() + "]";
		return output;
	}

	/** Reconnaitre un message de type //message;args... */
	public final static Pattern MSG_PATTERN = Pattern.compile("^" + IDENTIFIER
			+ "([a-zA-Z -]+)" + SEP + "?(.*)$");

	/** Reconnaitre un arguments de type args;arg=value;... */
	public final static Pattern ARG_PATTERN = Pattern
			.compile("^([a-zA-Z -]+)=?([^;]*)$");

	public static ServiceMessage parseServiceMessage(String msg) {

		// enlever les espaces inutiles
		msg = msg.trim();

		// rechercher un message au bon format
		Matcher mMsg = MSG_PATTERN.matcher(msg);
		if (mMsg.matches()) {

			// creation d'un nouveau message avec son id
			ServiceMessage output = new ServiceMessage(mMsg.group(1)
					.toLowerCase().trim());

			if (mMsg.groupCount() > 1) {

				// rechercher des arguments au bon format
				for (String s : mMsg.group(2).split(SEP)) {
					s = s.trim();
					Matcher mArg = ARG_PATTERN.matcher(s);
					if (mArg.find()) {
						String arg = mArg.group(1).toLowerCase().trim();
						String val = "";
						if (mArg.groupCount() > 1) {
							val = mArg.group(2).trim();
						}
						output.setArg(arg, val);
					}
				}
			}

			return output;
		}

		// le format ne correspond pas, retour null
		return null;
	}
}
