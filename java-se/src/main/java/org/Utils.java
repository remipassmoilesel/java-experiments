package org;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.rmi.CORBA.Util;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Utils {

	private static long threadCount = 0;

	public static String askQuestionCLI(String question,
			String... possibleResponses) {

		// travailler la liste de réponses possibles
		String listStr = "(";
		List<String> listRep = new ArrayList<String>();
		for (int i = 0; i < possibleResponses.length; i++) {
			String str = possibleResponses[i].toLowerCase().trim();
			listRep.add(str);
			listStr += str + ",";
		}
		listStr += ")";

		Scanner systemInScan = new Scanner(System.in);
		String resp = "";

		printLineCLI(question + " " + listStr);
		while (true) {
			try {
				resp = systemInScan.next().trim().toLowerCase();
			} catch (Exception e) {
				// e.printStackTrace();
			}
			if (listRep.contains(resp)) {
				break;
			} else {
				printLineCLI("Réponse invalide " + listStr + ": " + resp);
			}
		}

		return resp;

	}

	public static void printLineCLI() {
		printLineCLI("");
	}

	public static void printLineCLI(String line) {
		System.out.println(line);
	}

	public static void showProgressBarCLI(float value, float max) {
		showProgressBarCLI(50, value, max);
	}

	public static void showProgressBarCLI(int sizeChars, float value, float max) {

		boolean debug = false;
		if (debug) {
			System.out.println(sizeChars);
			System.out.println(value);
			System.out.println(max);
		}

		// caractere vide dans la barre de progression
		String eC = " ";

		// caractere plein
		String pC = "=";

		// nombre de caracteres vides et pleins
		int pCars = value == 0 ? 0 : Math.round(value * sizeChars / max);
		int eCars = sizeChars - pCars;

		// affichage du pourcentage
		String percent = "["
				+ (value == 0 ? 0 : Math.round((value * 100f / max))) + " %]";

		String output = "|";
		for (int i = 0; i < pCars; i++) {
			output += pC;
		}
		for (int i = 0; i < eCars; i++) {
			output += eC;
		}

		output += "| " + percent + "\r";

		try {
			System.out.write(output.getBytes());
			if (value == max) {
				System.out.write("\n".getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void displayMap(Map map) {
		displayMap(map, 20);
	}

	public static void displayMap(Map map, int nbrDisplay) {
		int i = 0;
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Object k = it.next();
			Object v = map.get(k);

			System.out.println("#" + i + " - " + k + ": " + v);

			i++;

			if (nbrDisplay != -1 && i >= nbrDisplay) {
				break;
			}
		}
	}

	public static Map<String, Integer> sortByComparator(
			Map<String, Integer> unsortMap) {

		return sortByComparator(unsortMap, true);
	}

	/**
	 * Source: http://www.mkyong.com/, modifiée
	 * 
	 * @param unsortMap
	 * @param croissant
	 * @return
	 */
	public static Map<String, Integer> sortByComparator(
			Map<String, Integer> unsortMap, final boolean croissant) {

		// Convert Map to List
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(
				unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				if (croissant) {
					return (o1.getValue()).compareTo(o2.getValue());
				} else {
					return -(o1.getValue()).compareTo(o2.getValue());
				}
			}
		});

		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static void addTitleLabel(String title, JComponent comp) {
		comp.add(new JLabel("<html><h2>" + title + "</h2></html>"), "wrap");
	}

	public static void addLabel(String string, JComponent comp) {
		comp.add(new JLabel("<html>" + string + "</html>"), "wrap");
	}

	public static File classToFile(Class classObj) {

		String path = "./src/main/java/"
				+ classObj.getName().replaceAll("\\.", "/") + ".java";
		return new File(path);
	}

	public static void runLater(Runnable runnable) {
		Thread t = new Thread(runnable);
		t.setName(Util.class.getName() + "_" + threadCount);
		t.start();

		threadCount++;
	}

	public static Random rand = new Random();

	public static int getRand(int min, int max) {
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static String join(String sep, List list) {

		String result = new String();

		// iterer la liste d'objets
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {

			// recuperer l'objet
			Object object = iterator.next();

			// ajouter au resultat final
			result += object.toString();

			// ajouter le separateur si necessaire
			if (iterator.hasNext()) {
				result += sep;
			}

		}

		return result;

	}

	/**
	 * From https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/
	 * Levenshtein_distance#Java
	 * 
	 * @param lhs
	 * @param rhs
	 * @return
	 */
	public int levenshteinDistance(CharSequence lhs, CharSequence rhs) {
		int len0 = lhs.length() + 1;
		int len1 = rhs.length() + 1;

		// the array of distances
		int[] cost = new int[len0];
		int[] newcost = new int[len0];

		// initial cost of skipping prefix in String s0
		for (int i = 0; i < len0; i++)
			cost[i] = i;

		// dynamically computing the array of distances

		// transformation cost for each letter in s1
		for (int j = 1; j < len1; j++) {
			// initial cost of skipping prefix in String s1
			newcost[0] = j;

			// transformation cost for each letter in s0
			for (int i = 1; i < len0; i++) {
				// matching current letters in both strings
				int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

				// computing cost for each transformation
				int cost_replace = cost[i - 1] + match;
				int cost_insert = cost[i] + 1;
				int cost_delete = newcost[i - 1] + 1;

				// keep minimum cost
				newcost[i] = Math.min(Math.min(cost_insert, cost_delete),
						cost_replace);
			}

			// swap cost/newcost arrays
			int[] swap = cost;
			cost = newcost;
			newcost = swap;
		}

		// the distance is the cost for transforming all letters in both strings
		return cost[len0 - 1];
	}

	/**
	 * Augmenter la qualité de rendu d'un objet graphics
	 * 
	 * @param g2d
	 */
	public static void applyQualityRenderingHints(Graphics2D g2d) {

		HashMap<Key, Object> hints = new HashMap<RenderingHints.Key, Object>();
		hints.put(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		hints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		hints.put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHints(hints);

	}

	public static ArrayList<Path> getAllFilesFrom(Path path) throws IOException {

		ArrayList<Path> output = new ArrayList<Path>();

		DirectoryStream<Path> stream = Files.newDirectoryStream(path);
		Iterator<Path> it = stream.iterator();
		while (it.hasNext()) {
			Path f = it.next();
			if (Files.isDirectory(f) == false) {
				output.add(f);
			}
		}

		return output;
	}

}
