package org.learn.messageHandlers;

import java.util.ArrayList;
import java.util.HashMap;

import org.learn.Mood;
import org.learn.message.HandleResponse;
import org.learn.message.MessageManager;
import org.learn.message.ServiceHandler;
import org.learn.message.ServiceMessage;

public class ConversationServiceHandler extends ServiceHandler {

	static {
		// charger tous les élements de conversation disponibles, et les garder
		// à disposition pour toutes les instances
	}

	@Override
	public HandleResponse handle(MessageManager dests, ServiceMessage message) {

		if (Commands.learn.equals(message.getMessageID())) {

			HashMap<String, String> args = message.getArgs();

			// afficher de l'aide sur demande
			if (args.containsKey(Commands.help)) {
				dests.sendMessage("Usage: //learn;k=mots, clefs;p=Phrase !;md=200");
				return HandleResponse.COMPLETED;
			}

			// verifier si il y a des mots clefs
			if (args.containsKey(Commands.keywords_short) == false) {
				dests.sendMessage("Vous devez spécifier des mots-clefs: //req/k=mots, clefs/...");
				return HandleResponse.ERROR;
			}
			ArrayList<String> keywords = new ArrayList<String>();
			for (String k : args.get("k").split(",")) {
				keywords.add(k.trim());
			}

			// verifier si il y a une phrase
			if (args.containsKey(Commands.phrase_short) == false) {
				dests.sendMessage("Vous devez spécifier une phrase: //req/p=Une phrase/...");
				return HandleResponse.ERROR;
			}
			String phrase = args.get("p").trim();

			// verifier ou attribuer une humeur
			int mood = -1;
			if (args.containsKey("md")) {
				try {
					mood = Integer.valueOf(args.get("md"));
				} catch (Exception e) {
					dests.sendMessage("Si vous spécifiez une humeur, elle doit être au "
							+ "format numérique et entre 0 et 200: //req/md=150/...");
					return HandleResponse.ERROR;
				}
			} else {
				mood = Mood.NORMAL;
			}

			// enregistrer l'interaction
			DialogEntity de = new DialogEntity();
			de.setMood(mood);
			de.setKeywords(keywords);
			de.setResponse(phrase);

			DialogEntityManager.addEntity(de);

			// imprimer un compte rendu ou de l'aide
			dests.sendMessage("Compris ! " + System.lineSeparator() + de);

			return HandleResponse.COMPLETED;
		}

		return HandleResponse.ERROR;
	}

	@Override
	public HandleResponse acceptMessage(MessageManager dests,
			ServiceMessage message) {

		if (Commands.learn.equals(message.getMessageID())) {
			return HandleResponse.ACCEPTED;
		}

		return HandleResponse.REJECTED;
	}
}
