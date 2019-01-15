package org.learn;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

import org.Utils;
import org.learn.message.Message;
import org.learn.message.MessageManager;

public class ConsoleUI extends MessageManager {

	/** Liste des message reçus. */
	private ArrayList<Message> messages;

	private JFrame gui;
	private JPanel mainPane;
	private JTextArea txtLearn;
	private JScrollPane txtLearnScroll;
	private JTextField txtSend;

	public ConsoleUI() {

		managerID = "me";

		this.messages = new ArrayList<Message>();

		// construction du GUI
		constructGui();

		// afficher la date de lancement
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String message = "Launching: " + sdf.format(new Date())
				+ System.lineSeparator();

		// afficher la dernière date de modification de la classe
		File file = Utils.classToFile(LearnAndSpeak.class);
		if (file.isFile()) {
			message += "Last modification of Learn: "
					+ sdf.format(file.lastModified());
		}

		else {
			message += "Unable to find :" + file.getPath();
		}

		messageIncoming(new Message(ConsoleUI.class.getSimpleName(), message));

	}

	private void constructGui() {

		gui = new JFrame();
		gui.setTitle("Learn");

		// contenu
		mainPane = new JPanel(new MigLayout("insets 15, gap 15"));
		Utils.addTitleLabel("Learn", mainPane);

		// affichage de la conversation
		txtLearn = new JTextArea();
		txtLearn.setLineWrap(true);
		txtLearn.setEditable(false);
		txtLearnScroll = new JScrollPane(txtLearn);
		mainPane.add(txtLearnScroll, "width 400px!, height 300px!, wrap 20px");

		// zone de texte de reponse
		txtSend = new JTextField();
		txtSend.addKeyListener(new SendTextKeyListener());
		mainPane.add(txtSend, "width 400px!, wrap");

		gui.setContentPane(mainPane);

		// propriètès de la fenêtre
		gui.setResizable(false);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.pack();
		gui.setLocationRelativeTo(null);

	}

	public void showGui() {
		gui.setVisible(true);
	}

	private class SendTextKeyListener implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				// récupérer le message
				String message = txtSend.getText();

				// vider la barre
				txtSend.setText("");

				// affichage du message
				messageIncoming(newMessage(message));

				// envoi du message
				sendMessage(message);

			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

	}

	@Override
	public String messageIncoming(Message message) {

		// enregistrer le message
		messages.add(message);

		// reconstruire l'affichage
		txtLearn.append("** " + message.getFrom() + ": " + message.getText()
				+ System.lineSeparator());

		// scroll bas
		// JScrollBar vertical = txtLearnScroll.getVerticalScrollBar();
		// vertical.setValue(vertical.getMaximum());
		txtLearn.setCaretPosition(txtLearn.getDocument().getLength());

		// rafraichir le tout
		txtLearn.revalidate();
		txtLearn.repaint();

		txtSend.requestFocusInWindow();

		return MessageManager.RECEIVED;
	}
}
