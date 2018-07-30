package org.startupmanager;

import javax.swing.SwingUtilities;

/**
 * Application de création d'un script Unix de démarrage avec ouverture
 * d'applications et de documents.
 * 
 * @author remipassmoilesel
 *
 */
public class Launcher {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FileManager fm = new FileManager();
				MainView mv = new MainView(fm);
				fm.setView(mv);
				mv.setVisible(true);

				mv.updateView();
			}
		});
	}
}
