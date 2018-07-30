package org.extractressources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe d'analyse et d'extraction de données. La classe cherche des mots ou
 * des ressources particulières.
 * 
 * @author remipassmoilesel
 *
 */
public abstract class TextAnalyser {

	private static final int MIN_WORD_SIZE = 2;

	protected FileOutput wordOutput;
	protected FileOutput ressourceOutput;

	/** Pattern de séparation de mots */
	private Pattern wordPattern = Pattern.compile("[^-a-zA-Z0-9+]");

	/** Pattern d'extraction de mails */
	private Pattern mailPattern = Pattern
			.compile("([A-Za-z0-9]+[-A-Za-z0-9_-]*@[\\.A-Za-z0-9_-]+)");

	/** Pattern d'extraction d'URL */
	private Pattern urlPattern = Pattern
			.compile("([a-z]{2,7}://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+)");

	private Pattern[] extractsPatterns = new Pattern[] { mailPattern,
			urlPattern, };

	/** Description de la source des ressources enregistrées */
	protected String sourceDescription = "";

	public abstract void analyse() throws IOException;

	protected void extractWordsAndRessources(String chunk) {

		String[] words = wordPattern.split(chunk);

		if (wordOutput == null || ressourceOutput == null) {
			throw new IllegalStateException("No output specified: "
					+ wordOutput + " " + ressourceOutput);
		}

		// filtrer les mots
		for (String w : words) {
			if (w.length() > MIN_WORD_SIZE) {
				try {
					wordOutput.out(w);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for (int j = 0; j < extractsPatterns.length; j++) {
			Pattern ep = extractsPatterns[j];
			Matcher m = ep.matcher(chunk);

			while (m.find()) {
				for (int i = 0; i < m.groupCount(); i++) {
					String w = m.group(i);
					try {
						ressourceOutput.out(w + "\n" + sourceDescription
								+ "\n\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void setWordOutput(FileOutput out) {
		this.wordOutput = out;
	}

	public void setRessourceOutput(FileOutput out) {
		this.ressourceOutput = out;
	}

}
