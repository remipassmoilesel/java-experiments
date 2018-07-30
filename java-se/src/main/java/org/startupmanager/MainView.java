package org.startupmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.Utils;

import net.miginfocom.swing.MigLayout;

/**
 * Vue de l'application de création d'un script de démarrage
 * 
 * @author remipassmoilesel
 *
 */
public class MainView extends JFrame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FileManager fm = new FileManager();
				MainView mv = new MainView(fm);
				fm.setView(mv);
				mv.setVisible(true);

				mv.updateView();
			}
		});
	}

	private static final Dimension FRAME_SIZE = new Dimension(600, 400);
	private JPanel mainPane;
	private JTextArea documentsTA;
	private JTextArea commandsTA;
	private FileManager manager;
	private JTextArea previTA;
	private JLabel errorLabel;

	public MainView(FileManager manager) {

		this.manager = manager;

		// construction du GUI
		constructGui();

		// propriètès de la fenêtre
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		this.setLocationRelativeTo(null);
	}

	private void constructGui() {

		// contenu
		mainPane = new JPanel(new MigLayout());
		Utils.addTitleLabel("Startup file", mainPane);

		// documents
		Utils.addLabel("Documents:", mainPane);
		documentsTA = new JTextArea();
		documentsTA.addKeyListener(new CustomKeyListener());
		mainPane.add(new JScrollPane(documentsTA),
				"width 400px!, height 100px!, wrap");

		// programmes
		Utils.addLabel("Commands:", mainPane);
		commandsTA = new JTextArea();
		commandsTA.addKeyListener(new CustomKeyListener());
		mainPane.add(new JScrollPane(commandsTA),
				"width 400px!, height 100px!, wrap");

		// previsualisation
		Utils.addLabel("File:", mainPane);
		previTA = new JTextArea();
		previTA.setEditable(false);
		previTA.addKeyListener(new CustomKeyListener());
		mainPane.add(new JScrollPane(previTA),
				"width 400px!, height 100px!, wrap");

		// espace d'affichage en cas d'erreur
		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.red);
		errorLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 11));

		mainPane.add(errorLabel, "center, wrap");

		// panneau des boutons
		JPanel buttonsPane = new JPanel();

		// bouton quitter
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.exitProgram();
			}
		});
		buttonsPane.add(btnQuit);

		// bouton ouvrir
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(MainView.this);
				if (JFileChooser.APPROVE_OPTION == returnVal) {
					manager.openFile(fc.getSelectedFile().toPath());
				}
			}
		});
		buttonsPane.add(btnOpen);

		// bouton sauvegarder
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manager.saveFile();
			}
		});
		buttonsPane.add(btnSave);

		// bouton sauvegarder sous
		JButton btnSaveAs = new JButton("Save as");
		btnSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(MainView.this);
				if (JFileChooser.APPROVE_OPTION == returnVal) {
					MainView.this.manager.saveFileAs(fc.getSelectedFile()
							.toPath());
				}
			}
		});
		buttonsPane.add(btnSaveAs);

		mainPane.add(buttonsPane, "center, wrap");

		setContentPane(mainPane);
	}

	public void showErrorMessage(final String message) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				errorLabel.setText("<html>" + message + "</html>");
				errorLabel.repaint();
			}
		});

	}

	/**
	 * Mettre à jour le formulaire en fonction du controleur
	 */
	public void updateView() {

		// mise à jour des documents
		String docStr = "";
		for (String s : manager.getDocuments()) {
			docStr += s + System.lineSeparator();
		}

		if (documentsTA.isFocusOwner() == false) {
			documentsTA.setText(docStr);
		}

		// mise à jour des commandes
		String pgrmStr = "";
		for (String s : manager.getCommands()) {
			pgrmStr += s + System.lineSeparator();
		}
		if (commandsTA.isFocusOwner() == false) {
			commandsTA.setText(pgrmStr);
		}

		// mise à jour de la prévi
		previTA.setText(manager.getFileAsString());

		// rafraichir
		documentsTA.repaint();
		commandsTA.repaint();
		previTA.repaint();

	}

	private class CustomKeyListener implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			manager.refreshFile(documentsTA.getText(), commandsTA.getText());
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}
	}

}
