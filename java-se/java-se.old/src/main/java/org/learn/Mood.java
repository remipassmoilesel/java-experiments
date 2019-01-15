package org.learn;

import org.Utils;

/**
 * Echelle d'humeur. Représentée par un entier.
 * 
 * @author remipassmoilesel
 *
 */
public class Mood {

	public static final int GOOD_MOOD = 0;
	public static final int NORMAL = 100;
	public static final int BAD_MOOD = 200;

	public static boolean isGoodMood(int mood) {
		return mood <= NORMAL;
	}

	public static boolean isBadMood(int mood) {
		return mood > NORMAL;
	}

	public static int getRandomMood() {
		return Utils.getRand(0, 200);
	}

}
