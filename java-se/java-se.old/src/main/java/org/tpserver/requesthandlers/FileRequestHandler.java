package org.tpserver.requesthandlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.tpserver.Connexion;
import org.tpserver.Log;

/**
 * Gestionnaire de requêtes sur fichiers. La requête est acceptée uniquement si
 * le fichier existe.
 * 
 * @author remipassmoilesel
 *
 */
public class FileRequestHandler extends AbstractRequestHandler {

	@Override
	public boolean isRequestSupported(Connexion c) {

		// verifier si la requete est de méthode GET
		if (c.isGetHttpMethod() == false) {
			return false;
		}

		// vérifier si l'url fournie est un fichier
		Path p = Paths.get(c.getRootPath(), c.getAskedRessource());

		return Files.isReadable(p) && Files.isDirectory(p) == false;
	}

	@Override
	public ProcessResponse processConnexion(Connexion c) {

		if (isRequestSupported(c) == false) {
			return ProcessResponse.ERROR;
		}

		InputStream is = null;
		OutputStream os = null;
		try {

			// récuperer les ressources
			Path p = Paths.get(c.getRootPath(), c.getAskedRessource());
			is = Files.newInputStream(p);

			os = c.getOutputStream();

			// envoyer les headers avec le type mime
			c.sendNormalHeaders(Files.probeContentType(p));

			// lecture / ecriture tamponnée
			byte[] buff = new byte[256];
			int nbr = 0;
			while ((nbr = is.read(buff)) != -1) {
				os.write(buff, 0, nbr);
			}

			// vider le writer
			os.flush();

		}

		// erreur lors de la lecture / ecriture
		catch (IOException e) {
			Log.error(e);
			return ProcessResponse.ERROR;

		}

		// fermeture des ressources
		finally {
			try {
				if (is != null) {
					is.close();
				}

			} catch (IOException e) {
				Log.error(e);
			}
		}

		return ProcessResponse.REQUEST_COMPLETED;

	}
}
