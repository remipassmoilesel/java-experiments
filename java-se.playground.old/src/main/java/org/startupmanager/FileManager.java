package org.startupmanager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Controlleur de l'application de fichier de démarrage.
 * 
 * @author remipassmoilesel
 *
 */
public class FileManager {

	private StartupFile startupfile;
	private MainView mainview;

	public FileManager() {
		newFile();
	}

	/**
	 * Rafraichir le fichier à partir de texte saisi par l'utilisateur
	 * 
	 * @param documents
	 * @param commands
	 */
	public void refreshFile(String documents, String commands) {
		startupfile.parseDocuments(documents);
		startupfile.parseCommands(commands);

		mainview.updateView();
	}

	public void newFile() {
		this.startupfile = new StartupFile();
		if (mainview != null) {
			mainview.updateView();
		}
	}

	public void saveFile() {

		if (startupfile.getPath() == null) {
			showErrorInView("You must save as before.");
			return;
		}

		saveFileAs(startupfile.getPath());
	}

	public void saveFileAs(Path file) {

		try {
			startupfile.save(file);
		} catch (IOException e) {
			showErrorInView("Error while writing: " + file);
		}

		mainview.updateView();
	}

	public void openFile(Path file) {

		try {
			startupfile.load(file);
		} catch (IOException e) {
			showErrorInView("Error while loading: " + file);
		}

		mainview.updateView();
	}

	public void showErrorInView(final String message) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				mainview.showErrorMessage(message);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				mainview.showErrorMessage("");
			}
		});

		t.start();

	}

	public ArrayList<String> getDocuments() {
		return startupfile.getDocuments();
	}

	public ArrayList<String> getCommands() {
		return startupfile.getCommands();
	}

	public void setView(MainView mv) {
		this.mainview = mv;
	}

	public String getFileAsString() {
		return startupfile.generateFileAsString();
	}

	public void exitProgram() {
		mainview.dispose();
	}

}
