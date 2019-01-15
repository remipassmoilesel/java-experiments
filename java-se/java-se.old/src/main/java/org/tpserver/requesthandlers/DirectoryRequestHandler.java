package org.tpserver.requesthandlers;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.tpserver.Connexion;
import org.tpserver.Log;

/**
 * Gestionnaire de requêtes sur fichiers. La requête est acceptée uniquement si
 * le fichier existe.
 * 
 * @author remipassmoilesel
 *
 */
public class DirectoryRequestHandler extends AbstractRequestHandler {

	@Override
	public boolean isRequestSupported(Connexion c) {

		// verifier si la requete est de méthode GET
		if (c.isGetHttpMethod() == false) {
			return false;
		}

		// vérifier si l'url fournie est un fichier
		Path p = Paths.get(c.getRootPath(), c.getAskedRessource());

		return Files.isReadable(p) && Files.isDirectory(p);
	}

	@Override
	public ProcessResponse processConnexion(Connexion c) {

		if (isRequestSupported(c) == false) {
			return ProcessResponse.ERROR;
		}

		// lister les fichiers et dossiers
		Path ap = Paths.get(c.getRootPath(), c.getAskedRessource());

		String contentOutput = "";
		Path parent = ap.getParent();
		if (parent != null) {
			contentOutput = "Dossier parent: <a href='" + getLinkFor(parent, c)
					+ "'>" + parent.toString() + "</a><br/>";

		}
		DirectoryStream<Path> dst;
		try {
			dst = Files.newDirectoryStream(ap);
		} catch (IOException e) {
			return ProcessResponse.ERROR;
		}

		// trier les répertoire et les fichiers
		ArrayList<Path> directories = new ArrayList<Path>();
		ArrayList<Path> files = new ArrayList<Path>();
		for (Path path : dst) {
			if (Files.isDirectory(path)) {
				directories.add(path);
			}

			else {
				files.add(path);
			}
		}

		// mettre en forme et afficher
		for (Path p : directories) {
			contentOutput += "<a href='" + getLinkFor(p, c) + "'>"
					+ p.getFileName().toString() + " / </a><br/>";
		}

		for (Path p : files) {
			contentOutput += "<a href='" + getLinkFor(p, c) + "'>"
					+ p.getFileName().toString() + " </a><br/>";
		}

		try {
			c.sendHtmlPage("Répertoire: " + ap.toString(), contentOutput);
		} catch (IOException e) {
			Log.error(e);
			return ProcessResponse.ERROR;
		}

		return ProcessResponse.REQUEST_COMPLETED;

	}

	private String getLinkFor(Path p, Connexion c) {

		String output = "http://" + c.getAdress().getHostName() + ":"
				+ c.getPortNumber() + "/";

		if (p.toString().length() > 2) {
			output += p.toString().substring(2);
		}

		return output;

	}
}
