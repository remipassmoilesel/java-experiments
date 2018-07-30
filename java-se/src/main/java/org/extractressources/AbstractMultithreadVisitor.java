package org.extractressources;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Wrapper de FileVisitor multi-threadé qui alloue un fichier au file visitor
 * uniquement si aucun thread ne s'occupe déjà du fichier.
 * 
 * @author remipassmoilesel
 *
 */
public abstract class AbstractMultithreadVisitor extends Observable {

	/** Liste de fichiers en cours d'analyse */
	private static ArrayList<Path> inspectedFiles = new ArrayList<Path>();

	/** Le nombre de threads à activer */
	private int threadNumber;

	/** Le chemin source à analyse */
	private Path path;

	/** Les visiteurs threadés actifs */
	private ArrayList<ThreadedFileVisitor> visitors;

	public AbstractMultithreadVisitor(int threads, Path path) {
		this.threadNumber = threads;
		this.path = path;
	}

	/**
	 * Analyser les fichiers de la racine sur plusieurs threads. La méthode
	 * bloque le thread courant jusqu'à la fin du traitement.
	 */
	public void processFiles() {

		// reinitialiser les listes de synchronisation
		visitors = new ArrayList<ThreadedFileVisitor>();
		inspectedFiles.clear();

		// lancer un visiteur par thread
		for (int i = 0; i < threadNumber; i++) {
			ThreadManager.run(new ThreadedFileVisitor());
		}

		// attendre la fin du travail
		while (workIsDone() == false) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Méthode à overrider pour manipuler un fichier dans une arborescence.
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	protected FileVisitResult processFile(Path arg0, BasicFileAttributes arg1) {
		throw new IllegalStateException("Must override this !");
	}

	/**
	 * Retourne vrai si et seulement si tous les threads comptabilisés sont
	 * terminés.
	 * 
	 * @return
	 */
	public boolean workIsDone() {

		synchronized (visitors) {

			int finished = 0;
			for (ThreadedFileVisitor t : visitors) {
				if (t.isWorkDone()) {
					finished++;
				}
			}

			return finished == threadNumber;
		}

	}

	/**
	 * Parcours les fichiers et appelle la méthode processFile uniquement si le
	 * fichier n'a pas déjà été traité.
	 * 
	 * @author remipassmoilesel
	 *
	 */
	private class ThreadedFileVisitor implements FileVisitor<Path>, Runnable {

		private boolean workIsDone;

		/**
		 * Méthode appelée pour créer et lancer le visiteur threadé
		 */
		@Override
		public void run() {

			workIsDone = false;

			try {

				// enregistrer le thread
				synchronized (visitors) {
					visitors.add(ThreadedFileVisitor.this);
				}

				// parcourir l'arborescence
				Files.walkFileTree(path, new ThreadedFileVisitor());

				// notifier de la fin
				workIsDone = true;

				synchronized (AbstractMultithreadVisitor.this) {
					AbstractMultithreadVisitor.this.notifyAll();
				}

			} catch (IOException e) {
				Log.error(e);
			}
		}

		/**
		 * Retourne vrai si le travail est terminé dans ce thread.
		 * 
		 * @return
		 */
		public boolean isWorkDone() {
			return workIsDone;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path arg0, IOException arg1)
				throws IOException {
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path arg0,
				BasicFileAttributes arg1) throws IOException {

			synchronized (inspectedFiles) {
				if (inspectedFiles.contains(arg0) == false) {
					inspectedFiles.add(arg0);

					return processFile(arg0, arg1);
				}
			}

			return FileVisitResult.CONTINUE;

		}

		@Override
		public FileVisitResult visitFile(Path arg0, BasicFileAttributes arg1)
				throws IOException {

			synchronized (inspectedFiles) {
				if (inspectedFiles.contains(arg0) == false) {
					inspectedFiles.add(arg0);

					return processFile(arg0, arg1);
				}
			}

			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path arg0, IOException arg1)
				throws IOException {
			return FileVisitResult.CONTINUE;
		}

	}

	public Path getPath() {
		return path;
	}

}
