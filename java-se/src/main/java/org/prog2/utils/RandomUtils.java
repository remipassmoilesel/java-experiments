package org.prog2.utils;

import java.util.Random;

public class RandomUtils {

	public static void main(String[] args) {
		Random rnd = new Random();

		// entier entre 0 et 5, 5 exclu
		System.out.println(rnd.nextInt(5));

		// entre 0 et 1
		System.out.println(rnd.nextDouble());

		// vrai ou faux
		System.out.println(rnd.nextBoolean());
	}

}
