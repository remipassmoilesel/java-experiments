package org.extractressources;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileOutput {

	/** Le temps entre les appels de méthode flush, en ms */
	private static final long GAP_BETWEEN_FULSH_MS = 20;

	/** Le chemin du fichier ou ecrire */
	private Path path;

	/** Le charset utilisé pour écrire */
	private String charset;

	/** L'objet d'écriture */
	private BufferedWriter writer;

	/** La dernière utilisation de la méthode flush, en ms */
	private long lastFlush = System.currentTimeMillis();

	private ArrayList<String> words;

	/** Si vrai les doublons ne seront pas écrits */
	private boolean filterOutput;

	public FileOutput(Path path) throws IOException {

		// créer le fichier si besoin
		this.path = path;
		if (Files.exists(path) == false) {
			Files.createFile(path);
		}

		// charset et writer
		this.charset = "utf-8";
		writer = Files.newBufferedWriter(path, Charset.forName(charset));

		// filtre de doublons
		filterOutput = true;
		this.words = new ArrayList<String>();
	}

	public synchronized void out(String chars) throws IOException {

		if (filterOutput) {
			if (words.contains(chars) == false) {
				writer.write(chars + "\n");
				words.add(chars);
			}
		}

		else {
			writer.write(chars + "\n");
		}

		if (System.currentTimeMillis() - lastFlush > GAP_BETWEEN_FULSH_MS) {
			writer.flush();
			lastFlush = System.currentTimeMillis();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (writer != null) {
			writer.close();
		}
	}

	public Path getPath() {
		return path;
	}

}
