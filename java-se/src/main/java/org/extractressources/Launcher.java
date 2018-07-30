package org.extractressources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import org.Utils;
import org.apache.commons.cli.ParseException;

/**
 * Module d'analyse et d'extraction d'informations à partir d'un système de
 * fichier. Le module créé un dossier avec deux types de résultats: des mots et
 * des ressources (url, mail, ...)
 * 
 * @author remipassmoilesel
 *
 */
public class Launcher {

	public static void main(String[] args) throws IOException, ParseException {

		start(args);
	}

	public static void start(String[] args) throws IOException, ParseException {

		/*
		 * Lister tous les dossiers concernés
		 */
		Utils.printLineCLI();
		Utils.printLineCLI("Analyse des dossiers disponibles...");
		Utils.printLineCLI();

		String[] strPaths = new String[] { "c:/utilisateurs", "c:/users",
				"/home", "." };

		boolean addAll = Utils.askQuestionCLI(
				"Ajouter tous les dossiers disponibles ?", "o", "n")
				.equals("o");

		ArrayList<Path> paths = new ArrayList<Path>();
		for (String str : strPaths) {
			Path p = Paths.get(str);
			if (Files.isDirectory(p)) {
				boolean add = true;
				if (addAll == false) {
					add = Utils
							.askQuestionCLI("Ajouter: " + p + " ?", "o", "n")
							.equals("o");
				}
				if (add) {
					paths.add(p);
				}
			}
		}

		/*
		 * Lister les fichiers de chaque dossier concerné
		 */
		Utils.printLineCLI();
		Utils.printLineCLI("Recherche des fichiers ...");
		Utils.printLineCLI();

		ArrayList<MultiThreadRessourceExtracter> listingVisitors = new ArrayList<MultiThreadRessourceExtracter>();
		Utils.showProgressBarCLI(0, paths.size());

		for (Path p : paths) {

			// créer un visiteur pour lister les fichiers
			MultiThreadRessourceExtracter mre = new MultiThreadRessourceExtracter(
					4, p);
			mre.doListOnly(true);
			mre.processFiles();

			listingVisitors.add(mre);

			Utils.showProgressBarCLI(paths.indexOf(p), paths.size());
		}

		Utils.showProgressBarCLI(paths.size(), paths.size());

		/*
		 * Afficher les fichiers à analyser
		 */

		Utils.printLineCLI();
		Utils.printLineCLI("Fichiers à analyser: ");
		Utils.printLineCLI();

		for (MultiThreadRessourceExtracter mre : listingVisitors) {
			Utils.printLineCLI(mre.getPath() + ": "
					+ mre.getListedFilesNumber() + ", "
					+ mre.getIgnoredFilesNumber() + " ignoré(s).");
		}

		Utils.printLineCLI();
		boolean exit = Utils.askQuestionCLI("Continuer ?", "o", "n")
				.equals("n");
		if (exit) {
			System.exit(0);
		}

		// Fichiers de résultats
		Path tempDir = Paths.get("./search-"
				+ new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss")
						.format(new Date()));

		if (Files.isDirectory(tempDir) == false) {
			Files.createDirectories(tempDir);
		}

		FileOutput ressources = new FileOutput(Paths.get(tempDir.toString(),
				"ressources"));
		FileOutput words = new FileOutput(
				Paths.get(tempDir.toString(), "words"));

		int i = 0;
		for (int j = 0; j < paths.size(); j++) {

			// le chemin a traiter
			Path p = paths.get(i);

			// le nombre total de fichiers
			final int totalFilesCount = listingVisitors.get(i)
					.getListedFilesNumber();

			// affichage
			Utils.printLineCLI();
			Utils.printLineCLI("Analyse en cours: " + (i + 1) + " / "
					+ listingVisitors.size());
			Utils.printLineCLI(p.toString());

			// créer un visiteur pour lister les fichiers
			MultiThreadRessourceExtracter mre = new MultiThreadRessourceExtracter(
					4, p);

			mre.setRessourceOutput(ressources);
			mre.setWordOutput(words);

			ProgressBarObserver pbobserver = new ProgressBarObserver(
					totalFilesCount);

			mre.addObserver(pbobserver);
			mre.processFiles();

			pbobserver.printLastProgressbarState();

			i++;
		}

		Utils.printLineCLI();
		Utils.printLineCLI("Terminé !");

		System.exit(0);

	}

	/**
	 * Ecoute les visiteurs de fichier et affiche une barre de progression.
	 * 
	 * @author remipassmoilesel
	 *
	 */
	private static class ProgressBarObserver implements Observer {

		private long lastUpdate = 0;
		private float total;

		public ProgressBarObserver(int total) {
			this.total = total;
		}

		@Override
		public void update(Observable observable, Object paramObject) {

			if (System.currentTimeMillis() - lastUpdate > 30) {

				Utils.showProgressBarCLI(
						((MultiThreadRessourceExtracter) observable)
								.getProcessedFilesNumber(), total);

				lastUpdate = System.currentTimeMillis();

			}

		}

		public void printLastProgressbarState() {
			Utils.showProgressBarCLI(total, total);
		}

	}
}
