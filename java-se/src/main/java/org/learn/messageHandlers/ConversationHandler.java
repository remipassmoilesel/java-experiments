package org.learn.messageHandlers;

import java.util.TreeSet;

import org.Utils;
import org.learn.message.HandleResponse;
import org.learn.message.Message;
import org.learn.message.MessageHandler;
import org.learn.message.MessageManager;

public class ConversationHandler extends MessageHandler {

	@Override
	public HandleResponse handle(MessageManager dests, Message msg) {

		TreeSet<DialogEntity> sortedList = new TreeSet<DialogEntity>();
		for (DialogEntity de : DialogEntityManager.getAllDialogs()) {

			int sumCoeff = 0;
			for (String s : de.getKeywords()) {
				if (msg.getText().contains(s)) {
					sumCoeff++;
				}
			}

			de.setCoeff(sumCoeff);

			sortedList.add(de);
		}

		// celui qui correspond le mieux
		DialogEntity first = sortedList.first();

		msg = dests.newMessage("");

		if (first.getCoeff() == 0) {
			msg.setText("J'ai rien compris !");
		}

		else {
			msg.setText(first.getResponse());
		}

		int waitMin = 300;
		int waitMax = 800;
		int wait = Utils.getRand(waitMin, waitMax);
		
		dests.sendMessage(msg, wait);

		return HandleResponse.ACCEPTED;
	}

	@Override
	public HandleResponse acceptMessage(MessageManager dests, Message message) {
		return HandleResponse.ACCEPTED;
	}

}
