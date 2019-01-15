package org.prog2.files;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileVisit {

	public static void main(String[] args) {
		countFiles(".");

		System.out.println(getDirectorySize(Paths.get(".")));

		try {
			CustomFileVisitor cfv = new CustomFileVisitor();
			Files.walkFileTree(Paths.get("."), cfv);

			System.out.println(cfv.getTotal());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void countFiles(String pathStr) {

		DirectoryStream<Path> stream;
		try {
			stream = Files.newDirectoryStream(Paths.get(pathStr));

			int files = 0;

			for (Path file : stream) {
				if (Files.isDirectory(file) == false) {
					files++;
				}
			}

			System.out.println("Nombre de fichiers: " + files);

		} catch (IOException e) {
			System.out.println("Dossier invalide: " + pathStr);
		}

	}

	/**
	 * Compter la taille d'un dossier récursivement
	 * 
	 * @param path
	 * @return
	 */
	public static long getDirectorySize(Path path) {

		// Path est un fichier
		if (Files.isRegularFile(path)) {

			long size = 0;

			try {
				size = Files.size(path);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return size;
		}

		// Path est un dossier
		else {

			long total = 0;

			DirectoryStream<Path> stream;
			try {
				stream = Files.newDirectoryStream(path);
				for (Path p : stream) {
					total += getDirectorySize(p);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return total;
		}

	}

}
