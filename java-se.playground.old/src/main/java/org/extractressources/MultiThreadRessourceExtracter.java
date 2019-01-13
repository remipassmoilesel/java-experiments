package org.extractressources;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MultiThreadRessourceExtracter extends AbstractMultithreadVisitor {

	/** Si le visiteur liste uniquement, contient le nombre de fichiers visités */
	private int listedFiles;

	/** Si vrai, le visiteur ne fera que compter les fichiers */
	private boolean doListOnly;

	/**
	 * Si le visteur analyse les fichiers, contient le nombre de fichiers
	 * analysés
	 */
	private int processedFiles;

	/** Le chemin du dossier temporaire */
	private Path tempPath;

	/** Les filtres d'extension */
	private List<String> extensionFiltersStr;

	/** Les filtres de nom de fichier */
	private List<String> fileNameFiltersStr;

	/** Les filtres compilés */
	private ArrayList<Pattern> patternFilters;

	/** Si les filtres sont activés, contient le nombre de fichiers ignorés */
	private int ignoredFiles;

	private FileOutput ressourcesOutput;

	private FileOutput wordsOutput;

	public MultiThreadRessourceExtracter(int threads, Path path) {
		super(threads, path);

		doListOnly = false;

		// statistiques
		processedFiles = 0;
		listedFiles = 0;
		ignoredFiles = 0;

		// filtres par défaut
		extensionFiltersStr = Arrays.asList("doc", "pdf", "xml", "rtf", "jpg",
				"jpeg", "txt", "ppt", "xls");

		fileNameFiltersStr = Arrays.asList(".*password.*", ".*document.*");

		buildPatterns();
	}

	/**
	 * Construire des objets pattern à partir des filtres (chaines de
	 * caractères)
	 */
	public void buildPatterns() {

		patternFilters = new ArrayList<Pattern>();

		for (String r : extensionFiltersStr) {
			patternFilters.add(Pattern.compile(".*" + r + "$"));
		}

		for (String r : fileNameFiltersStr) {
			patternFilters.add(Pattern.compile(r));
		}

	}

	/**
	 * Affecter une sortie pour écrire les ressources extraites
	 * 
	 * @param ressourcesOutput
	 */
	public void setRessourceOutput(FileOutput ressourcesOutput) {
		ignoreTempPath(ressourcesOutput.getPath().getParent());
		this.ressourcesOutput = ressourcesOutput;
	}

	/**
	 * Affecter le chemin des fichiers temporaires pour l'ignorer
	 * 
	 * @param path
	 */
	public void ignoreTempPath(Path path) {
		tempPath = path;
	}

	/**
	 * Affecter une sortie pour les mots extraits
	 * 
	 * @param wordsOutput
	 */
	public void setWordOutput(FileOutput wordsOutput) {
		ignoreTempPath(wordsOutput.getPath().getParent());
		this.wordsOutput = wordsOutput;
	}

	/**
	 * Si vrai, les fichiers ne seront pas traités mais juste listés
	 * 
	 * @param val
	 */
	public void doListOnly(boolean val) {
		doListOnly = val;
		listedFiles = 0;
	}

	/**
	 * Retourne le nombre de fichiers listés
	 * 
	 * @return
	 */
	public int getListedFilesNumber() {
		return listedFiles;
	}

	/**
	 * Retourne le nombre de fichiers traités
	 * 
	 * @return
	 */
	public int getProcessedFilesNumber() {
		return processedFiles;
	}

	/**
	 * Retourne le nombre de fichiers ignorés
	 * 
	 * @return
	 */
	public int getIgnoredFilesNumber() {
		return ignoredFiles;
	}

	/**
	 * Analyse d'un fichier
	 */
	@Override
	protected FileVisitResult processFile(Path file, BasicFileAttributes arg1) {

		// ignorer les dossiers
		if (Files.isDirectory(file)) {
			return FileVisitResult.CONTINUE;
		}

		// ignorer les fichiers de résultats
		if (tempPath != null && file.startsWith(tempPath)) {
			return FileVisitResult.CONTINUE;
		}

		// filtrer les fichiers en fonction de leurs chemins
		if (patternFilters.isEmpty() == false) {
			boolean fileMatch = false;
			for (Pattern p : patternFilters) {
				if (p.matcher(file.toString()).find()) {
					fileMatch = true;
					break;
				}
			}

			if (fileMatch == false) {
				ignoredFiles++;
				return FileVisitResult.CONTINUE;
			}
		}

		// lister les fichiers uniquement
		if (doListOnly) {
			listedFiles++;
			return FileVisitResult.CONTINUE;
		}

		// ou analyse
		try {
			FileAnalyser analyser = new FileAnalyser(file, ressourcesOutput,
					wordsOutput);
			analyser.setSourceFile(file);
			analyser.analyse();

			processedFiles++;

			setChanged();
			notifyObservers();

		} catch (Exception e) {
			Log.error(e);
		}

		return FileVisitResult.CONTINUE;
	}

}
