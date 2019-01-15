package org.prog2.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.Utils;

public class WordCounter {

	public static void main(String[] args) throws IOException {

		WordCounter wordCounter = new WordCounter();
		wordCounter.countWordsFromFile("rougenoir.txt");

		Map<String, Integer> sortedWords = wordCounter.getSortedWords(false);
		System.out.println("Nombre de mots: " + sortedWords.size());
		Utils.displayMap(sortedWords, 20);

		System.out.println();

		HashMap<Integer, ArrayList<String>> specialSorted = wordCounter
				.getSpecialSortedWords();
		System.out.println("Tri spécial: ");
		Utils.displayMap(specialSorted, 20);

	}

	private HashMap<String, Integer> words;

	public WordCounter() {
		this.words = new HashMap<String, Integer>();
	}

	/**
	 * Compter les mots
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void countWordsFromFile(String path) throws IOException {

		Path p = Paths.get("rougenoir.txt");
		BufferedReader br = Files
				.newBufferedReader(p, Charset.forName("utf-8"));

		int c = 0;
		String word = "";
		while ((c = br.read()) != -1) {
			if (Character.isLetter(c) || c == '-') {
				word += (char) c;
			} else {
				if (word.equals("") == false)
					registerWord(word);
				word = "";
			}
		}

	}

	private void registerWord(String word) {

		word = word.toLowerCase();

		Integer k = words.get(word);

		if (k != null) {
			words.put(word, k + 1);
		}

		else {
			words.put(word, 1);
		}

	}

	public HashMap<String, Integer> getWords() {
		return new HashMap<String, Integer>(words);
	}

	public HashMap<Integer, ArrayList<String>> getSpecialSortedWords() {

		// la sortie
		HashMap<Integer, ArrayList<String>> output = new HashMap<Integer, ArrayList<String>>();

		Map<String, Integer> sortedWords = getSortedWords(false);

		// iterer les mots
		Iterator<String> it = sortedWords.keySet().iterator();
		while (it.hasNext()) {

			// recuperer les valeurs
			String word = it.next();
			Integer occurences = sortedWords.get(word);

			// tenter de récuperer le nombre d'occurence dans la liste resultat
			ArrayList<String> outputValue = output.get(occurences);

			// l'occurence n'existe pas, création
			if (outputValue == null) {
				ArrayList<String> newValue = new ArrayList<String>();
				newValue.add(word);
				output.put(occurences, newValue);
			}

			// l'occurence existe, ajout du mot
			else {
				outputValue.add(word);
			}
		}

		return output;
	}

	public Map<String, Integer> getSortedWords(boolean croissant) {
		Map<String, Integer> sortedMap = Utils.sortByComparator(words,
				croissant);
		return sortedMap;
	}

}
