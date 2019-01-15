package org.tpserver.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import junit.framework.Assert;

import org.junit.Test;
import org.tpserver.Connexion;
import org.tpserver.requesthandlers.FileRequestHandler;

public class ServerTests {

	/**
	 * Tests de la classe FileRequestHandler
	 */
	@Test
	public void testFileHandler() {

		/**
		 * Vérfier la methode isRequestSupported
		 */
		Connexion con1 = createGetRequest("/lorem_ipsum", ".");
		Connexion con2 = createGetRequest("/not/a/file", ".");

		FileRequestHandler frh = new FileRequestHandler();

		Assert.assertTrue(con1.getAskedRessource(),
				frh.isRequestSupported(con1));

		Assert.assertFalse(con1.getAskedRessource(),
				frh.isRequestSupported(con2));

	}

	/**
	 * Tests de la classe Connection
	 */
	@Test
	public void testConnection() {

		/**
		 * Vérifier l'extraction de chemin de ressource de la premiere ligne de
		 * requete.
		 */
		// ressource type
		// GET /test/de/ressources?param=hello%20world HTTP/1.1

		// la ressource demandée
		String res = "/test/de/ressources?param=hello%20world";

		// créer la connection
		Connexion con1 = createGetRequest(res, "");

		// verification
		Assert.assertEquals("Extraction de chemin de ressource: ", res,
				con1.getAskedRessource());

	}

	/**
	 * Utilitaire pour tests
	 * 
	 * @param res
	 * @param rootPath
	 * @return
	 */
	private Connexion createGetRequest(String res, String rootPath) {

		// le tableau de ligne à traiter
		ArrayList<String> sr = new ArrayList<String>();
		sr.add("GET " + res + " HTTP/1.1");

		// créer la connection
		Connexion c;
		try {
			c = new Connexion(null, rootPath);
		} catch (IOException e) {
			return null;
		}
		c.setStringRequest(sr);

		// renvoyer la connexion
		return c;
	}
}
