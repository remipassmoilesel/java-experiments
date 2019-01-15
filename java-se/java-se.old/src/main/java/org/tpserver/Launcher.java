package org.tpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.tpserver.requesthandlers.CinemaApiHandler;
import org.tpserver.requesthandlers.ConsoleDisplayHandler;
import org.tpserver.requesthandlers.DirectoryRequestHandler;
import org.tpserver.requesthandlers.FileRequestHandler;

/**
 * Travaux pratiques sur la réalisation d'un serveur simple. Classe de lancement
 * et de paramétrage du serveur
 * 
 * @author remipassmoilesel
 *
 */
public class Launcher {

	private static int port;

	public static void main(String[] args) throws IOException {

		// le port d'écoute
		port = 4040;

		System.out.println("Lancement de l'écoute du port: " + port);

		// le gestionnaire d'écoute
		SocketManager sm = new SocketManager(port);
		sm.addService(ConsoleDisplayHandler.class);
		sm.addService(FileRequestHandler.class);
		sm.addService(DirectoryRequestHandler.class);
		sm.addService(CinemaApiHandler.class);

		// le repertoire racine
		sm.setRootPath(".");

		// ecouter le port
		sm.listen();

		// simulation de connexions
		// simulation();

		// // statistiques
		// while (true) {
		//
		// sm.printReport();
		//
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// Log.error(e);
		// }
		// }

	}

	/**
	 * Simuler la connexion de nombreux clients.
	 */
	public static void simulation() {

		int connexionsPerThread = 100;
		int threads = 550;

		for (int i = 0; i < threads; i++) {
			Thread t = new Thread(new TrafficThread(connexionsPerThread));
			t.start();
		}

	}

	private static class TrafficThread implements Runnable {

		/** Le nombre de connexions */
		private int connexions;

		/** Liste de connexions possibles */
		private static final ArrayList<String> REQUESTS = new ArrayList<String>();

		{
			String baseUrl = "http://127.0.0.1:" + port;
			REQUESTS.add(baseUrl + "/src");
			REQUESTS.add(baseUrl + "/target");
			// REQUESTS.add(baseUrl + "/not/a/ressource");
			REQUESTS.add(baseUrl + "/pom.xml");
			REQUESTS.add(baseUrl + "/image.png");
		}

		public TrafficThread(int connexions) {
			this.connexions = connexions;
		}

		public void run() {

			for (int i = 0; i < connexions; i++) {

				// choisir une requete au hasard
				String req = REQUESTS
						.get(new Random().nextInt(REQUESTS.size()));

				// execution
				try {
					executeAndRead(req);
				} catch (IOException e1) {
					Log.error(e1);
				}

				// attendre avant la prochaine
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					Log.error(e);
				}

			}

		}

		private void executeAndRead(String req) throws IOException {

			URL obj = new URL(req);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		}
	}

}
