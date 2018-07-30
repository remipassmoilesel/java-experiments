package org.tpserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Conteneur de connexion socket.
 * 
 * @author remipassmoilesel
 *
 */
public class Connexion {

	private static final Pattern ASKED_RESS = Pattern
			.compile("^\\S+ +([^ ]+).*");

	/** Stocker la connexion pour E/S */
	private Socket socket;

	/** La premiere requete sous forme de liste de chaines */
	private ArrayList<String> stringRequest;

	/** Le chemin du dossier racine concernée par la connexion */
	private String rootPath;

	/** L'adresse du socket */
	private InetAddress inetAdress;

	/** Le port du socket */
	private int portNumber;

	public Connexion(Socket socket, String rootPath) throws IOException {
		this.socket = socket;
		this.stringRequest = readAll();
		this.rootPath = rootPath;

		this.inetAdress = socket.getInetAddress();
		this.portNumber = socket.getLocalPort();
	}

	@Override
	public String toString() {

		Object[] values = new Object[] { socket, stringRequest, rootPath,
				portNumber, inetAdress };
		Object[] keys = new Object[] { "socket", "stringRequest", "rootPath",
				"portNumber", "url" };

		String output = this.getClass().getName() + " [";
		for (int i = 0; i < keys.length; i++) {
			output += values[i] + ": " + keys[i] + ", ";
		}
		output += "]";

		return output;
	}

	/**
	 * Retourne une liste des lignes de la toute première requête ou null si le
	 * socket n'est pas valide.
	 * 
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> readAll() throws IOException {

		if (socket == null) {
			return null;
		}

		// ouvrir un flux à partir du socket
		BufferedReader br = getBufferedReader();

		// lire toutes les lignes
		ArrayList<String> output = new ArrayList<String>();
		String line = "";
		while ((line = br.readLine()) != null) {
			if (line.equals("")) {
				break;
			}
			output.add(line);
		}

		// sortie
		return output;

	}

	/**
	 * Attention: ne pas fermer les ressources ou les sockets seront fermés
	 * également.
	 * 
	 * @return
	 * @throws IOException
	 */
	public BufferedWriter getBufferedWriter() throws IOException {
		return new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
	}

	/**
	 * Attention: ne pas fermer les ressources ou les sockets seront fermés
	 * également.
	 * 
	 * @return
	 * @throws IOException
	 */
	public BufferedReader getBufferedReader() throws IOException {
		return new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * Attention: ne pas fermer les ressources ou les sockets seront fermés
	 * également.
	 * 
	 * @return
	 * @throws IOException
	 */
	public OutputStream getOutputStream() throws IOException {
		return socket.getOutputStream();
	}

	/**
	 * Ecrit une chaine de caracteres dans le socket. Attention: ne pas fermer
	 * les ressources ou les sockets seront fermés également.
	 * 
	 * @param str
	 * @throws IOException
	 */
	public void write(String str) throws IOException {

		// ouvrir un flux à partir du socket
		BufferedWriter bw = getBufferedWriter();

		// ecrire
		bw.write(str);

		// vider le flux
		bw.flush();

	}

	/**
	 * Envoi des headers pour une réponse code 200.
	 * 
	 * @param mimeType
	 * @throws IOException
	 */
	public void sendNormalHeaders(String mimeType) throws IOException {
		write(getHeaders(200, mimeType));
	}

	/**
	 * Retourne des headers
	 * 
	 * @param code
	 * @param mimeType
	 */
	private String getHeaders(int code, String mimeType) {

		ArrayList<String> headers = new ArrayList<String>();
		headers.add("HTTP/1.1 " + code);
		headers.add("Date: " + getServerTime());
		headers.add("Expires: -1");
		headers.add("Content-Type: " + mimeType + "; charset=utf-8");
		headers.add("");

		String output = "";
		for (String h : headers) {
			output += h + System.lineSeparator();
		}

		return output;
	}

	/**
	 * Retourne le contenu de la requete originale sous forme de liste de lignes
	 * ou null.
	 * 
	 * @return
	 */
	public ArrayList<String> getStringRequest() {
		return stringRequest;
	}

	/**
	 * A n'utiliser que pour les tests.
	 * 
	 * @param stringRequest
	 */
	@Deprecated
	public void setStringRequest(ArrayList<String> stringRequest) {
		this.stringRequest = stringRequest;
	}

	/**
	 * Renvoi vrai si la requete est de méthode "method"
	 * 
	 * @param c
	 * @return
	 */
	public boolean isHttpMethod(String method) {

		// vérifier la requete premiere
		if (stringRequest == null || stringRequest.size() <= 0) {
			return false;
		}

		// récupérer la première ligne
		String l = stringRequest.get(0);

		// renvoyer vrai si la chaine "get" apparait
		return l.toLowerCase().indexOf(method) != -1;

	}

	/**
	 * Renvoi vrai si de type GET
	 * 
	 * @param c
	 * @return
	 */
	public boolean isGetHttpMethod() {
		return isHttpMethod("get");
	}

	/**
	 * Retourne le chemin demandé par la méthode.
	 * 
	 * @return
	 */
	public String getAskedRessource() {

		// GET /test/de/ressources?param=hello%20world HTTP/1.1

		// vérifier la requete premiere
		if (stringRequest == null || stringRequest.size() <= 0) {
			return null;
		}

		// regex pour retrouver la ressource demandée
		Matcher matcher = ASKED_RESS.matcher(stringRequest.get(0));

		if (matcher.find() == false || matcher.groupCount() < 1) {
			return null;
		}

		return matcher.group(1);

	}

	/**
	 * Retourne le chemin de la racine de la requête.
	 * 
	 * @return
	 */
	public String getRootPath() {
		return rootPath;
	}

	/**
	 * Retourne l'heure pour headers HTTP
	 * 
	 * @return
	 */
	private String getServerTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss z", Locale.FRANCE);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * Ferme le socket.
	 */
	public void closeSocket() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				Log.error(e);
			}
		}
	}

	/**
	 * Envoi une page d'erreur 404
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void send404HtmlPage(String msg) throws IOException {

		// construire la page
		HtmlPage hp = new HtmlPage();
		hp.setTitle("Erreur 404");
		hp.setContent(msg);

		// envoyer les headers
		write(getHeaders(404, "text/html"));

		// envoyer la page
		write(hp.serialize());

	}

	public void sendHtmlPage(String title, String content) throws IOException {

		// construire la page
		HtmlPage hp = new HtmlPage();
		hp.setTitle(title);
		hp.setContent(content);

		// envoyer les headers
		write(getHeaders(200, "text/html"));

		// envoyer la page
		write(hp.serialize());
	}

	public InetAddress getAdress() {
		return inetAdress;
	}

	public int getPortNumber() {
		return portNumber;
	}
}
