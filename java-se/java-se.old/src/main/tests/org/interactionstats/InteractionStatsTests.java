package org.interactionstats;

import junit.framework.Assert;

import org.junit.Test;

public class InteractionStatsTests {

	/**
	 * Vérifier la validité des id
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testId() {
		InteractionStatsManager.generateStatUnit("onlyLettersOrNumbers!");
	}

	/**
	 * Vérifier que deux unités ne possèdent pas un même id
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNotTwoSameUnits() {
		InteractionStatsManager.generateStatUnit("a");
		InteractionStatsManager.generateStatUnit("a");
	}

	/**
	 * Vérifier la sortie
	 */
	@Test
	public void testOutput() {
		InteractionStatsManager.reset();

		InteractionStatsManager.generateStatUnit("a");
		InteractionStatsManager.generateStatUnit("b");
		InteractionStatsManager.generateStatUnit("c");
		InteractionStatsManager.generateStatUnit("d");

		for (int i = 0; i < 40; i++) {
			InteractionStatsManager.incrementUnit("a");
			InteractionStatsManager.incrementUnit("b");
			InteractionStatsManager.incrementUnit("c");
		}

		String actual = InteractionStatsManager.statsUnitsToString();
		Assert.assertEquals("Bad output: " + actual, actual, "a:40&b:40&c:40&");
	}
}
