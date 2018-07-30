package org.learn;

import org.Utils;
import org.learn.message.HandleResponse;
import org.learn.message.Message;
import org.learn.message.MessageHandler;
import org.learn.message.MessageManager;
import org.learn.message.ServiceHandler;
import org.learn.message.ServiceMessage;
import org.learn.messageHandlers.ConversationHandler;
import org.learn.messageHandlers.ConversationServiceHandler;

public class LearnAndSpeak extends MessageManager {

	private long delayBeforeResponseMS = 300;
	private int mood;

	public LearnAndSpeak() {

		this.managerID = "Learn";
		this.mood = Mood.getRandomMood();

	}

	@Override
	public String messageIncoming(final Message message) {

		// vérifier si le message est un message de service
		final ServiceMessage serviceMessage = ServiceMessage
				.parseServiceMessage(message.getText());
		
		if (serviceMessage != null) {

			// Confirmer la réception du message
			final String output = serviceMessage.toString();

			// traiter le message de service
			Utils.runLater(new Runnable() {
				@Override
				public void run() {
					// collationner
					sendMessage(new Message(managerID, output),
							delayBeforeResponseMS);
					// gerer la requete
					handleServiceMessage(serviceMessage);
				}
			});
		}

		// le message n'est pas un message de service
		else {

			Utils.runLater(new Runnable() {
				@Override
				public void run() {
					handleMessage(message);
				}
			});

		}

		return MessageManager.RECEIVED;
	}

	/**
	 * Liste des services disponibles
	 */
	private static final ServiceHandler[] AVAILABLES_SERVICES = new ServiceHandler[] { new ConversationServiceHandler() };

	private void handleServiceMessage(ServiceMessage serviceMessage) {

		// Recherche d'un handler exclusif. Si un exclusif est trouvé, gestion
		// du message puis arret.
		boolean handled = false;
		for (ServiceHandler s : AVAILABLES_SERVICES) {

			if (HandleResponse.ACCEPTED_EXCLUSIVE.equals(s.acceptMessage(this,
					serviceMessage))) {
				s.handle(this, serviceMessage);
				return;
			}

			if (HandleResponse.ACCEPTED.equals(s.acceptMessage(this,
					serviceMessage))) {
				s.handle(this, serviceMessage);
				handled = true;
			}

		}

		// aucun service trouvé, erreur
		if (handled == false) {
			sendMessage(newMessage("Service inconnu: " + serviceMessage));
			throw new IllegalStateException("Unknown service: "
					+ serviceMessage);
		}

	}

	private static final MessageHandler[] AVAILABLES_HANDLERS = new MessageHandler[] { new ConversationHandler() };

	private void handleMessage(Message message) {

		// Recherche d'un handler exclusif. Si un exclusif est trouvé, gestion
		// du message puis arret.
		boolean handled = false;
		for (MessageHandler s : AVAILABLES_HANDLERS) {
			if (HandleResponse.ACCEPTED_EXCLUSIVE.equals(s.acceptMessage(this,
					message))) {
				s.handle(this, message);
				return;
			}

			if (HandleResponse.ACCEPTED.equals(s.acceptMessage(this, message))) {
				s.handle(this, message);
				handled = true;
			}
		}

		// aucun service trouvé, erreur
		if (handled == false) {
			sendMessage(newMessage("Message non traité: " + message));
			throw new IllegalStateException("Unknown service: " + message);
		}

	}

}
