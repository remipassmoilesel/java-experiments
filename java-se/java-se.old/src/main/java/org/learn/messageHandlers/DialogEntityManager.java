package org.learn.messageHandlers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class DialogEntityManager {

	public static final String HOME = System.getProperty("user.home");
	public static final Path ROOT = Paths.get(".", "learn");
	public static final Path DIALOG = Paths.get(ROOT.toString(), "dialog");

	private static ArrayList<DialogEntity> entites = new ArrayList<DialogEntity>();

	static {

		// chargement des entités à partir des fichiers
		loadEntities();
	}

	/**
	 * Enregistrer un element de dialogue
	 * 
	 * @param de
	 */
	public static void serialize(DialogEntity de) {

		// sérialiser l'objet
		XMLEncoder encoder = null;
		try {
			Path p = Paths.get(DIALOG.toString(), "dialog_" + de.hashCode());
			encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(p.toString())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		encoder.writeObject(de);
		encoder.close();

	}

	public static void addEntity(DialogEntity de) {

		// ajout à la collection globale
		entites.add(de);

		serialize(de);
	}

	public static void loadEntities() {

		checkFileSystem();

		entites = new ArrayList<DialogEntity>();

		Iterator<Path> iterator = null;
		try {
			iterator = Files.newDirectoryStream(DIALOG).iterator();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// sérialiser l'objet
		XMLDecoder decoder = null;

		while (iterator.hasNext()) {

			Path p = iterator.next();

			try {
				decoder = new XMLDecoder(new BufferedInputStream(
						new FileInputStream(p.toString())));
				entites.add((DialogEntity) decoder.readObject());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (decoder != null) {
					decoder.close();
				}
			}

		}

	}

	private static void checkFileSystem() {

		// vérifier et creer la racine au besoin
		if (Files.isDirectory(ROOT) == false) {
			try {
				Files.createDirectories(ROOT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// vérifier et creer le dossier dialogues
		if (Files.isDirectory(DIALOG) == false) {
			try {
				Files.createDirectories(DIALOG);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static ArrayList<DialogEntity> getAllDialogs() {
		return entites;
	}

}
