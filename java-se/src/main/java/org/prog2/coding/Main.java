package org.prog2.coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static final String LOREM_IPSUM = "lorem_ipsum.txt";

	public static void main(String[] args) throws IOException {

		// tryCoderOnFile(new LeetCoder());

		// tryCoderOnFile(new VignereCoder("abcd"));

		tryCoderOnFile(new NumCoder("abcd"));

		// tryCoder(NumCoder.class);

	}

	public static void tryCoder(Class<? extends Coder> coderClass) {

		Coder ncc = null;
		Coder ncd = null;
		if (NumCoder.class.equals(coderClass)) {
			ncc = new NumCoder("abcd");
			ncd = new NumCoder("abcd");
		}
		if (VignereCoder.class.equals(coderClass)) {
			ncc = new VignereCoder("abcd");
			ncd = new VignereCoder("abcd");
		}

		for (int i = 'a'; i <= 'z'; i++) {
			char coded = ncc.codeChar(i);
			char decoded = ncd.decodeChar(coded);

			System.out
					.println(i + " " + (char) i + " " + coded + " " + decoded);
		}

	}

	public static void tryCoderOnFile(Coder coder) throws IOException {

		Path pin = Paths.get(LOREM_IPSUM);
		Path pout = Paths.get(LOREM_IPSUM + "_"
				+ coder.getClass().getSimpleName());

		// lire le fichier en clair
		BufferedReader sr = Files.newBufferedReader(pin,
				Charset.forName("utf-8"));

		// et l'ecrire en codé
		CodeWriter cw = new CodeWriter(Files.newBufferedWriter(pout,
				Charset.forName("utf-8")), coder);

		char[] buff = new char[256];
		int nbr = 0;
		while ((nbr = sr.read(buff)) != -1) {
			cw.write(buff, 0, nbr);
		}

		cw.close();

		// reinitialiser le coder si necessaire
		coder.reset();

		// lire le fichier codé
		BufferedReader sro = Files.newBufferedReader(pout,
				Charset.forName("utf-8"));

		// afficher le fichier en clair
		CodeReader dr = new CodeReader(sro, coder);
		buff = new char[256];
		nbr = 0;
		while ((nbr = dr.read(buff)) != -1) {
			for (int i = 0; i < nbr; i++) {
				System.out.print(buff[i]);
			}
		}

		dr.close();
	}
}
