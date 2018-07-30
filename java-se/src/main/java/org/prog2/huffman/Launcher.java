package org.prog2.huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Exercice sur le codage de Huffman
 * @author remipassmoilesel
 *
 */
public class Launcher {

	public static void main(String[] args) {

		// try {
		// simpleTest();
		// } catch (ClassNotFoundException | IOException e) {
		// e.printStackTrace();
		// }

		// vérifier le nombre d'arguments
		if (args.length < 3) {
			quitAndShowError("Syntaxe incorrecte");
		}

		String option = args[0];

		if (option.equals("-e")) {
			try {
				code(args[1], args[2]);
			} catch (IOException e) {
				quitAndShowError("Erreur lors du codage de '" + args[1]
						+ "' vers '" + args[2] + "'");
				// e.printStackTrace();
			}
		}

		else if (option.equals("-d")) {
			try {
				decode(args[1], args[2]);
			} catch (ClassNotFoundException | IOException e) {
				quitAndShowError("Erreur lors du decodage de '" + args[1]
						+ "' vers '" + args[2] + "'");
				//e.printStackTrace();
			}
		}

		else {
			quitAndShowError("Argument invalide: " + option);
		}

	}

	private static void code(String source, String dest) throws IOException {

		// lirre la source, extraire les mots
		ArrayList<String> words = new ArrayList<String>();
		BufferedReader reader = Files.newBufferedReader(Paths.get(source),
				Charset.forName("utf-8"));

		char[] buff = new char[512];
		int readed = 0;
		StringBuffer sb = new StringBuffer(20);
		while ((readed = reader.read(buff)) != -1) {
			for (int i = 0; i < readed; i++) {
				char c = buff[i];
				if (Character.isAlphabetic(c)) {
					sb.append(c);
				}

				else {
					words.add(sb.toString());
					sb.delete(0, sb.length());
				}
			}
		}

		// serialisation de la source codée
		HuffmanCode<String> hc = new HuffmanCode<String>(words);
		ObjectOutputStream outputStream = new ObjectOutputStream(
				Files.newOutputStream(Paths.get(dest)));
		outputStream.writeObject(hc);
		outputStream.close();

	}

	private static void decode(String source, String dest) throws IOException, ClassNotFoundException {
		
		// lire la source et extraire l'arbre de huffman
		ObjectInputStream inputStream = new ObjectInputStream(
				Files.newInputStream(Paths.get(source)));
				
		HuffmanCode<String> hc2 = (HuffmanCode<String>) inputStream
				.readObject();
		inputStream.close();
		
		// ecrire les objets
		BufferedWriter output = Files.newBufferedWriter(Paths.get(dest), Charset.forName("utf-8"));
		
		for(String w : hc2.getElements()){
			output.write(w + " ");
		}

		output.close();
		
	}

	private static void quitAndShowError(String msg) {
		System.err.println("Erreur.");
		System.err.println("Message: " + msg);
		System.err.println("Usage: huffman (-e | -d) source destination");
		System.exit(1);
	}

	private static void simpleTest() throws IOException, ClassNotFoundException {

		// créer une liste de données aléatoires
		ArrayList<Integer> datas = new ArrayList<Integer>();
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			datas.add(rand.nextInt(10));
		}

		System.out.println("Données aléatoires: ");
		System.out.println(datas);

		HuffmanCode<Integer> hc = new HuffmanCode<Integer>(datas);

		System.out.println("Occurences: ");
		HashMap<Integer, Integer> occ = hc.countOccurences();
		System.out.println(occ);

		System.out.println("Dictionnaire: ");
		Map<Integer, String> dic = hc.getTree().getDictionnary();
		System.out.println(dic);

		Path p = Paths.get("huffmanObject.obj");
		System.out.println("Ecriture de l'objet: " + p);

		ObjectOutputStream outputStream = new ObjectOutputStream(
				Files.newOutputStream(p));
		outputStream.writeObject(hc);
		outputStream.close();

		System.out.println("Lecture de l'objet: " + p);
		ObjectInputStream inputStream = new ObjectInputStream(
				Files.newInputStream(p));
		HuffmanCode<Integer> hc2 = (HuffmanCode<Integer>) inputStream
				.readObject();
		inputStream.close();

		System.out.println("Objets enregistrés: ");
		System.out.println(hc.getElements());
		System.out.println(hc2.getElements());

	}

}
