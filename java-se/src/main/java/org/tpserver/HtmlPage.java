package org.tpserver;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Page HTML
 * 
 * @author remipassmoilesel
 *
 */
public class HtmlPage {

	private static final String STUB_NAME = "htmlstub.html";

	/**
	 * Squelette d'une page html
	 */
	private static String stub;
	{
		Path path = Paths.get(getClass().getResource(STUB_NAME).getPath());
		try {
			stub = new String(Files.readAllBytes(path));
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/** Le titre de la page */
	private String title;

	/* Le contenu de la page */
	private String content;

	public HtmlPage() {
		this.content = "";
		this.title = "";
	}

	/**
	 * Retourne cette page html sous forme de chaine de caractères
	 * 
	 * @return
	 */
	public String serialize() {

		String output = stub.replaceAll("%title%", title);
		output = output.replaceAll("%content%", content);

		return output;

	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
