package org.learn;

import javax.swing.SwingUtilities;

/**
 * Une petite application qui répond à des intéractions textuelles, et qui peut
 * apprendre certaines réponses.
 * 
 * @author remipassmoilesel
 *
 */
public class Launcher {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				LearnAndSpeak learn = new LearnAndSpeak();
				ConsoleUI console = new ConsoleUI();

				learn.addDestinations(console);
				console.addDestinations(learn);

				console.showGui();

				// /learn;k=salut,ca,va,bien;p=Salut ! Ca va merci et toi ?
			}
		});
	}

}
