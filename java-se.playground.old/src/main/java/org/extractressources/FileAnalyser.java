package org.extractressources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.Utils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.mozilla.universalchardet.UniversalDetector;
import org.xml.sax.SAXException;

/**
 * Classe d'analyse de fichiers. Pour les méthodes d'analyse, voir Analyser.
 * 
 * @author remipassmoilesel
 *
 */
public class FileAnalyser extends TextAnalyser {

	private UniversalDetector textParser = new UniversalDetector(null);
	private AutoDetectParser variousParser = new AutoDetectParser();

	private Path path;

	public FileAnalyser(Path source, FileOutput ressOut, FileOutput wordOut)
			throws IOException {

		setSourceFile(source);
		setRessourceOutput(ressOut);
		setWordOutput(ressOut);

	}

	@Override
	public void analyse() throws IOException {

		try {
			parseVariousFiles(path);
		} catch (SAXException | TikaException e) {
			parseTextFile(path);
			throw new IOException(e);
		}

	}

	public void parseVariousFiles(Path p) throws IOException, SAXException,
			TikaException {

		sourceDescription = "#" + p.toString();

		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();

		InputStream stream = Files.newInputStream(p);
		variousParser.parse(stream, handler, metadata);

		extractWordsAndRessources(metadata.toString());
		extractWordsAndRessources(handler.toString());

		// debuggage
		boolean debug = false;
		if (debug) {
			System.out.println();
			System.out.println(metadata.toString());

			System.out.println();
			System.out.println(handler.toString());
		}

	}

	public void parseTextFile(Path p) throws IOException {

		textParser.reset();

		InputStream is = Files.newInputStream(p);

		int maxiter = 3;
		int i = 0;
		byte[] buffer = new byte[256];
		int ret = 0;
		while (ret != -1 || i < maxiter) {
			ret = is.read(buffer);
			if (ret != -1)
				textParser.handleData(buffer, 0, ret);
			i++;
		}

		String charsetName = textParser.getDetectedCharset();
		Log.message("Fichier: " + p);
		Log.message("Charset détecté: " + charsetName);

		if (charsetName == null) {
			// Log.message("Ignoré: ");
			// continue;
			charsetName = "UTF-8";
			Log.message("Charset inconnu, essai avec: " + charsetName);
		}

		Charset charset = Charset.forName(charsetName);
		BufferedReader br = Files.newBufferedReader(p, charset);
		i = 0;
		buffer = new byte[256];
		String sret = "";
		while (sret != null) {
			extractWordsAndRessources(sret);

			sret = br.readLine();
			i++;
		}

	}

	public void parseSqlite() {
		// TODO
	}

	public void setSourceFile(Path p) throws IOException {
		this.path = p;
	}
}
