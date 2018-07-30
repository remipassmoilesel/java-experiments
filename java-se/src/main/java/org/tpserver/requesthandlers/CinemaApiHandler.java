package org.tpserver.requesthandlers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import org.tpserver.Connexion;
import org.tpserver.Log;

/**
 * Une classe permettant d'avoir le programmes des films d'auteur du soir ...
 * 
 * @author remipassmoilesel
 *
 */
public class CinemaApiHandler extends AbstractRequestHandler {

	/** Les données :) */
	private static final String LIST_NAME = "listefilms.txt";

	/** Permet de distinguer un appel à l'API du reste */
	public static final Pattern PATTERN = Pattern
			.compile("^/ghetto-production/?(json|xml|plain)?/?");

	/** Tirage au sort des résultats */
	public static final Random RANDOM = new Random();

	public static final ArrayList<String> GHETTO_FILMS = new ArrayList<String>();

	{
		Path path = Paths.get(getClass().getResource(LIST_NAME).getPath());
		try {
			GHETTO_FILMS.addAll(Files.readAllLines(path,
					Charset.forName("utf-8")));
		} catch (IOException e) {
			Log.error(e);
		}
	}

	@Override
	public boolean isRequestSupported(Connexion c) {
		String ar = c.getAskedRessource();

		if (ar == null) {
			return false;
		}

		return PATTERN.matcher(ar).find();
	}

	@Override
	public ProcessResponse processConnexion(Connexion c) {

		if (isRequestSupported(c) == false) {
			return ProcessResponse.ERROR;
		}

		int i = RANDOM.nextInt(GHETTO_FILMS.size());

		try {
			c.sendNormalHeaders("text/plain");
		} catch (IOException e) {
			Log.error(e);
			return ProcessResponse.ERROR;
		}

		try {
			c.write("Les productions du ghetto présentent: "
					+ GHETTO_FILMS.get(i));
		} catch (IOException e) {
			Log.error(e);
			return ProcessResponse.ERROR;
		}

		return ProcessResponse.REQUEST_COMPLETED;
	}

}
