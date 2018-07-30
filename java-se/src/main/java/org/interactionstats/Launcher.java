package org.interactionstats;

/**
 * Un module permettant de compter les interactions et de les concatener en une
 * chaine de caractère transmissible (par exemple dans une requete HTTP).
 * 
 * @author remipassmoilesel
 *
 */
public class Launcher {

	public static void main(String[] args) {

		InteractionStatsManager.generateStatUnit("a");
		InteractionStatsManager.generateStatUnit("b");
		InteractionStatsManager.generateStatUnit("c");
		InteractionStatsManager.generateStatUnit("d");

		for (int i = 0; i < 40; i++) {
			InteractionStatsManager.incrementUnit("a");
			InteractionStatsManager.incrementUnit("b");
			InteractionStatsManager.incrementUnit("c");
		}

		System.out.println(InteractionStatsManager.getStatUnit("a"));
		System.out.println(InteractionStatsManager.getStatUnit("b"));
		System.out.println(InteractionStatsManager.getStatUnit("c"));

		System.out.println(InteractionStatsManager.statsUnitsToString());
		// a40b40c40
	}

}
