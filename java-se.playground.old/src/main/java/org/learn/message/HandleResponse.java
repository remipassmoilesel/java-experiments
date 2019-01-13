package org.learn.message;

public enum HandleResponse {

	/**
	 * Le message est acceptée par le gestionnaire, et peut être transmis à
	 * d'autres gestionnaires
	 */
	ACCEPTED,

	/**
	 * Le message est accepté par le gestionnaire, et ne doit pas transmis à
	 * d'autres gestionnaires
	 */
	ACCEPTED_EXCLUSIVE,

	/**
	 * Le message est rejeté par le gestionnaire
	 */
	REJECTED,

	/**
	 * Erreur lors du traitement d'un message
	 */
	ERROR,

	/**
	 * Le message a été traité avec succés
	 */
	COMPLETED,

}
