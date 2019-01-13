package org.prog2.streams;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ReadPNG {

	/** La signature d'un fichier PNG */
	public static final int[] PNG_SIGNATURE = new int[] { 137, 80, 78, 71, 13,
			10, 26, 10 };

	public static void main(String[] args) throws IOException {

		String[] files = new String[] { "nothing.no", "image.png", "image2.png" };

		for (String f : files) {
			System.out.println("isPngFile(f): " + f);
			System.out.println(isPngFile(f));
			System.out.println(getPngMetadatas(f));
		}

	}

	/**
	 * Retourne les dimensions d'une image PNG ou null si le fichier n'est pas
	 * un PNG ou si une erreur intervient pendant la lecture.Pas de levée
	 * d'exceptions.
	 * 
	 * @param p
	 * @return
	 */
	public static HashMap<String, Integer> getPngMetadatas(String p) {

		/*
		 * Width: 4 bytes Height: 4 bytes Bit depth: 1 byte Color type: 1 byte
		 * Compression method: 1 byte Filter method: 1 byte Interlace method: 1
		 * byte
		 */

		// verifier si le fichier est un PNG
		if (isPngFile(p) == false) {
			return null;
		}

		// récupérer le path
		Path path = Paths.get(p);

		DataInputStream in = null;
		try {

			// ouvrir un flux de lecture
			in = new DataInputStream(new BufferedInputStream(
					Files.newInputStream(path)));

			// sauter les octets non significatifs
			in.skip(PNG_SIGNATURE.length + 8);

			HashMap<String, Integer> rslt = new HashMap<String, Integer>();

			// dimensions
			rslt.put("width", in.readInt());
			rslt.put("height", in.readInt());
			rslt.put("bit depth", in.read());
			rslt.put("color type", in.read());
			rslt.put("compression method", in.read());
			rslt.put("filter method", in.read());
			rslt.put("interlace method", in.read());

			return rslt;

		} catch (IOException e) {
			// erreur: retourner null
			e.printStackTrace();
			return null;
		}

		// fermer la ressources
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Retourner vrai si le fichier existe et si sa signature est celle d'un
	 * fichier PNG. Retourne faux dans les autres cas. Pas de levée
	 * d'exceptions.
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isPngFile(String p) {

		// récupérer le path
		Path path = Paths.get(p);

		// verifier si le fichier existe
		if (Files.exists(path) == false)
			return false;

		DataInputStream in = null;
		try {

			// ouvrir un flux de lecture
			in = new DataInputStream(new BufferedInputStream(
					Files.newInputStream(path)));

			// l'octet courant
			int o;

			for (int j = 0; j < PNG_SIGNATURE.length; j++) {

				// lire l'octet
				o = in.read();

				// l'octet est different, renvoyer faux
				if (o != PNG_SIGNATURE[j]) {
					return false;
				}

			}

			// pas d'erreurs et octets identiques: retourner vrai
			return true;

		} catch (IOException e) {
			// erreur: retourner faux
			e.printStackTrace();
			return false;
		}

		// fermer la ressources
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
