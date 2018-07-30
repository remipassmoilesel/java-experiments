package org.tpserver.requesthandlers;

import org.tpserver.Connexion;
import org.tpserver.Log;

/**
 * Parent de tous les gestionnaire de requetes. Contient des méthodes
 * utilitaires.
 * 
 * @author remipassmoilesel
 *
 */
public abstract class AbstractRequestHandler {

	/**
	 * Retourne une instance gestionnaire de requete disponible
	 * 
	 * @return
	 */
	public static Class<? extends AbstractRequestHandler>[] getAvailablesHandlers() {
		/*
		 * Attention: Les gestionnaires non exclusifs doivent être placés avant
		 * les autres ou ils ne seront pas systématiquement utilisés.
		 */

		return new Class[] {

				// gestionnaires filtres, à placer en premier
				ConsoleDisplayHandler.class,

				// gestionnaires classiques
				FileRequestHandler.class,
				//
				DirectoryRequestHandler.class,
				//
				CinemaApiHandler.class,

		};
	}

	/**
	 * 
	 * @author remipassmoilesel
	 *
	 */
	public enum ProcessResponse {
		/**
		 * A renvoyer pour signifier que la requête a été traitée mais qu'elle
		 * peut être traitée par un autre gestionnaire.
		 */
		REQUEST_FILTERED,
		/**
		 * A renvoyer pour signifier que la requête a été traitée mais qu'aucun
		 * autre gestionnaire ne doit la traiter.
		 */
		REQUEST_COMPLETED,

		/**
		 * Requete non prise en charge
		 */
		UNKNOWN_REQUEST,

		/**
		 * A renvoyer pour signifier une erreur lors du traitement
		 */
		ERROR
	};

	/**
	 * Retourne vrai si ce gestionnaire supporte cette connexion.
	 * 
	 * @param t
	 */
	public abstract boolean isRequestSupported(Connexion t);

	/**
	 * Traiter la connexion.
	 * 
	 * @param t
	 */
	public abstract ProcessResponse processConnexion(Connexion t);

}
