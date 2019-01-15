package org.startupmanager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fichier de démarrage. Le contenu est divisé en deux type: 1) les documents à
 * ouvrir 2) les commandes.
 * 
 * @author remipassmoilesel
 *
 */
public class StartupFile {

	private static final String DOCUMENT_COMMAND = "xdg-open";
	private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

	public static final Pattern pattern = Pattern.compile("^"
			+ DOCUMENT_COMMAND + " +\"([^\"]+)\" +&$");

	private static final String NOT_EMPTY_REGEX = ".*\\w+.*";

	private Path path;
	private ArrayList<String> documentslines;
	private ArrayList<String> commandsLines;

	public StartupFile() {
		documentslines = new ArrayList<String>();
		commandsLines = new ArrayList<String>();
		path = null;
	}

	public void save(Path path) throws IOException {

		String content = generateFileAsString();

		BufferedWriter wrt = Files.newBufferedWriter(path, DEFAULT_CHARSET);

		wrt.write(content);

		wrt.close();

		this.path = path;

	}

	public void load(Path path) throws IOException {

		List<String> lines = Files.readAllLines(path, DEFAULT_CHARSET);

		for (String l : lines) {

			// sauter les lignes inutiles
			if (l.length() < 1 || l.substring(0, 1).equals("#")) {
				continue;
			}

			// la ligne est un document
			Matcher m = pattern.matcher(l);
			if (m.find()) {
				documentslines.add(m.group(1));
			}

			// la ligne est une commande
			else {
				commandsLines.add(l);
			}

		}

		this.path = path;

	}

	public ArrayList<String> getDocuments() {
		return documentslines;
	}

	public ArrayList<String> getCommands() {
		return commandsLines;
	}

	public String generateFileAsString() {

		// entete
		String ls = System.lineSeparator();
		String output = "#!/bin/bash" + ls;
		output += ls;

		// documents
		output += "# Documents: " + ls;
		for (String l : documentslines) {
			if (l.matches(NOT_EMPTY_REGEX)) {
				output += DOCUMENT_COMMAND + " \"" + l + "\" &" + ls;
			}
		}

		output += ls;

		// programs
		output += "# Commands: " + ls;
		for (String l : commandsLines) {
			if (l.matches(NOT_EMPTY_REGEX)) {
				output += l + ls;
			}
		}

		return output;
	}

	public void parseCommands(String commands) {
		String[] lines = commands.split(System.lineSeparator());
		commandsLines = new ArrayList<String>(Arrays.asList(lines));
	}

	public void parseDocuments(String documents) {
		String[] lines = documents.split(System.lineSeparator());
		documentslines = new ArrayList<String>(Arrays.asList(lines));
	}

	public Path getPath() {
		return path;
	}
}
