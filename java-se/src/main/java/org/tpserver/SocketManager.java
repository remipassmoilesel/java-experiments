package org.tpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import org.tpserver.requesthandlers.AbstractRequestHandler;
import org.tpserver.requesthandlers.AbstractRequestHandler.ProcessResponse;

/**
 * Ecoute de socket et traitement de requêtes.
 * 
 * @author remipassmoilesel
 *
 */
public class SocketManager {

	/** Le numéro de port à écouter */
	private int port;

	/** La tache de répartition des requetes vers les gestionnaires */
	private PortListener portListener;

	/** Le nombre total de connexions */
	private long totalConnexions;

	/** Le nombre de connexions actives */
	private long activeConnexions;

	/** Le temps total de traitement des requêtes */
	private long totalTime;

	/** Le chemin de la racine du dossier servi */
	private String rootPath;

	private long maxActiveConnexions;

	private ArrayList<Class> services;

	/**
	 * Créer un gestionnaire de socket. L'écoute doit être lancée avec listen()
	 * 
	 * @param port
	 */
	public SocketManager(int port) {
		this.port = port;
		this.portListener = null;

		this.rootPath = ".";

		this.totalConnexions = 0;
		this.activeConnexions = 0;
		this.maxActiveConnexions = 0;
		this.totalTime = 0;

		this.services = new ArrayList<Class>();
	}

	/**
	 * Lancement de l'écoute du port passé en argument du constructeur. L'écoute
	 * se fait dans un thread différent.
	 */
	public void listen() {
		if (portListener == null || portListener.isListenning() == false) {
			portListener = new PortListener();
			ThreadManager.run(portListener);
		}
	}

	/**
	 * Ajouter un service de traitement de requête. Attention, un service non
	 * exclusif (filtrant) devra être ajouté avant tous les autres services
	 * exclusifs, sous peine de n'être jamais éxécuté.
	 * 
	 * @param hdl
	 */
	public void addService(Class hdl) {
		services.add(hdl);
	}

	/**
	 * Ecoute d'un socket et redirection des requêtes.
	 * 
	 * @author remipassmoilesel
	 *
	 */
	class PortListener implements Runnable {

		/** Vrai si le listener est en fonction */
		private boolean listenning = false;

		/**
		 * Traite la requête passée en argument avec le gestionnaire adapté ou
		 * renvoi faux si aucun gestionnaire ne correspond.
		 * 
		 * @param c
		 * @return
		 */
		private ProcessResponse handle(Connexion c) {

			// lister puis itérer les gestionnaires disponibles

			for (Class rhc : services) {

				// créer une instance du gestionnaire de requete
				AbstractRequestHandler rh = null;
				try {
					rh = (AbstractRequestHandler) rhc.newInstance();
				} catch (InstantiationException e) {
					Log.error(e);
					continue;
				} catch (IllegalAccessException e) {
					Log.error(e);
					continue;
				}

				// tester si le gestionnaire peut traiter la requete
				if (rh.isRequestSupported(c)) {

					// traiter la requete
					ProcessResponse rslt = rh.processConnexion(c);

					// si le gestionnaire est exclusif ou si une erreur
					// survient:
					// arret
					if (ProcessResponse.REQUEST_COMPLETED.equals(rslt)
							|| ProcessResponse.ERROR.equals(rslt)) {
						return rslt;
					}

				}

			}

			return ProcessResponse.UNKNOWN_REQUEST;

		}

		public void run() {

			listenning = true;

			// ouvrir une connexion
			ServerSocket s = null;
			try {

				s = new ServerSocket(port);

				while (listenning) {

					// stocker la connexion
					final Connexion c = new Connexion(s.accept(), rootPath);

					// decompte statistique
					totalConnexions++;

					// trouver un manager adapté et traiter la connexion
					ThreadManager.run(new Runnable() {
						public void run() {

							// calcul du temps de traitement des requêtes
							long t = System.currentTimeMillis();

							// decompte statistique
							activeConnexions++;
							if (maxActiveConnexions > activeConnexions) {
								maxActiveConnexions = activeConnexions;
							}

							// traitement de la requete
							ProcessResponse resp = handle(c);

							if (ProcessResponse.ERROR.equals(resp)) {
								try {
									c.send404HtmlPage("Erreur lors du traitement de votre requête.");
								} catch (IOException e) {
									Log.error(e);
								}
							}

							else if (ProcessResponse.UNKNOWN_REQUEST
									.equals(resp)) {
								try {
									c.send404HtmlPage("Requête indisponible ou non gérée.");
								} catch (IOException e) {
									Log.error(e);
								}
							}

							// decompte statistique
							activeConnexions--;

							// fermeture de la connexion
							c.closeSocket();

							// calcul du temps de traitement des requêtes
							totalTime += (System.currentTimeMillis() - t);

						}
					});

					// arret de l'ecoute
					if (listenning == false) {
						break;
					}
				}
			}

			catch (IOException e) {
				Log.error(e);
			}

			finally {

				if (s != null) {
					try {
						s.close();
					} catch (IOException e) {
						Log.error(e);
					}
				}

				listenning = false;

			}

		}

		public boolean isListenning() {
			return listenning;
		}

		public void setListenning(boolean listenning) {
			this.listenning = listenning;
		}

	}

	public void printReport() {

		System.out.println("Rapport de fonctionnement:");
		System.out.println("Nombre total de requêtes: " + totalConnexions);
		System.out.println("Nombre max de requêtes actives: "
				+ activeConnexions);
		System.out.println("Temps total de traitement: " + (totalTime / 1000d)
				+ " s");
		System.out.println("Temps moyen: " + (totalTime / totalConnexions)
				+ " ms");

		System.out.println();

	}

	public boolean isListenning() {
		return portListener == null ? false : portListener.isListenning();
	}

	public void setListenning(boolean listenning) {
		if (portListener != null) {
			portListener.setListenning(false);
		}
	}

	public long getTotalConnexions() {
		return totalConnexions;
	}

	public long getActiveConnexions() {
		return activeConnexions;
	}

	public String getRootPath() {
		return rootPath;
	}

	/**
	 * Definir le répertoire racine de service.
	 * 
	 * @param rootFolder
	 */
	public void setRootPath(String path) {
		this.rootPath = path;
	}

}
