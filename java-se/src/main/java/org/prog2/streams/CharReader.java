package org.prog2.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Lit un fichier caractère par caractère.
 * 
 * @author remipassmoilesel
 *
 */
public class CharReader {

	private static final String CHARSET = "utf-8";
	public static final String LOREM_IPSUM = "lorem_ipsum.txt";

	public static void main(String[] args) throws IOException {

		System.out.println("toUpperCase(LOREM_IPSUM)");
		System.out.println(toUpperCase(LOREM_IPSUM));

		System.out.println("countOccurences(LOREM_IPSUM)");
		System.out.println(countOccurences(LOREM_IPSUM));

	}

	public static String toUpperCase(String path) throws IOException {

		// récupérer le chemin et vérifier qu'il existe
		Path p = Paths.get(path).toRealPath();

		// ouvrir un flux entrant
		BufferedReader in = Files
				.newBufferedReader(p, Charset.forName(CHARSET));

		String rslt = "";

		int c = 0;
		while ((c = in.read()) != -1) {
			rslt += String.valueOf((char) c).toUpperCase();
		}

		return rslt;

	}

	public static HashMap<String, Integer> countOccurences(String path)
			throws IOException {

		// récupérer le chemin et vérifier qu'il existe
		Path p = Paths.get(path).toRealPath();

		// ouvrir un flux entrant
		BufferedReader in = Files
				.newBufferedReader(p, Charset.forName(CHARSET));

		HashMap<String, Integer> occurences = new HashMap<>();

		int c = 0;
		while ((c = in.read()) != -1) {

			String s = String.valueOf((char) c);

			int occ = occurences.containsKey(s) ? occurences.get(s) : 0;
			occurences.put(s, ++occ);

		}

		return occurences;

	}
}
