package org.interactionstats;

import java.util.ArrayList;

/**
 * Gestion des objets de mesure d'intéraction
 * 
 * @author remipassmoilesel
 *
 */
public class InteractionStatsManager {

	private static final String SEPARATOR = "&";
	
	private static InteractionStatsManager singleton = new InteractionStatsManager();
	private ArrayList<InteractionStatUnit> units;

	/** Identifiant par défaut incrémenté à chaque nouvelle création */
	private static int numId = 0;

	private InteractionStatsManager() {
		this.units = new ArrayList<InteractionStatUnit>();
	}

	/**
	 * Transformer la liste des elements de statistiques en une chaine de
	 * caracteres
	 * 
	 * @return
	 */
	public static String statsUnitsToString() {

		StringBuilder sb = new StringBuilder(singleton.units.size() * 2);

		for (InteractionStatUnit u : singleton.units) {
			if (u.getInteractions() != 0) {
				sb.append(u.toString() + SEPARATOR);
			}
		}

		return sb.toString();
	}

	/**
	 * Retourne l'unité de comptage d'interaction correspondant à l'id spécifié
	 * ou en crée une nouvelle si aucune n'a été trouvée.
	 * 
	 * @param id
	 * @return
	 */
	public static InteractionStatUnit getStatUnit(String id) {

		// chercher une unité correspondant à l'id
		for (InteractionStatUnit u : singleton.units) {
			if (u.getId().equals(id)) {
				return u;
			}
		}

		// aucune trouvée, retourner un nouvelle unité
		return generateStatUnit(id);

	}

	/**
	 * Générer une entité avec identifiant numérique
	 * 
	 * @return
	 */
	public static InteractionStatUnit generateStatUnit() {

		String id = null;
		InteractionStatUnit isu;
		do {
			id = ++numId + "";
			isu = new InteractionStatUnit(id);
		} while (singleton.units.contains(isu));

		singleton.units.add(isu);

		return isu;
	}

	/**
	 * Créer une nouvelle unité
	 * 
	 * @param id
	 * @return
	 */
	public static InteractionStatUnit generateStatUnit(String id) {

		InteractionStatUnit isu = new InteractionStatUnit(id);
		if (singleton.units.contains(isu)) {
			throw new IllegalArgumentException("ID already present: " + id);
		}

		singleton.units.add(isu);

		return isu;
	}

	/**
	 * Incrémenter l'element de comptage d'id spécifié en paramètre
	 * 
	 * @param id
	 */
	public static void incrementUnit(String id) {
		getStatUnit(id).incrementInteractions();
	}

	/**
	 * Retourne la liste des elements de comptage.
	 * 
	 * @return
	 */
	public static ArrayList<InteractionStatUnit> getStatUnits() {
		return singleton.units;
	}

	public static void reset() {
		singleton.units.clear();
	}

}
