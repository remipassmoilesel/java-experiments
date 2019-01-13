package org.prog2.graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

import org.Utils;

/**
 * La fin d'un TP, une petite interface qui illustre l'example de l'algo de
 * Dijkstra de Wikipédia. Loin d'être optimal.
 * <p>
 * Source: https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra
 * 
 * @author remipassmoilesel
 *
 */
public class GraphGui extends JFrame {

	private JTextField txtDepart;
	private JTextField txtArrival;
	private JLabel lblError;
	private Graph graph;
	private GraphPane graphPane;

	/**
	 * Constructeur de la fenêtreF
	 * 
	 * @param graph
	 */
	public GraphGui(Graph graph) {

		this.graph = graph;

		// panneau principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new MigLayout("insets 20, gap 5"));
		setContentPane(mainPanel);

		mainPanel
				.add(new JLabel(
						"<html><h2>Le plus court chemin ...</h2></html>"),
						"span, wrap");

		// champs texte depart et arrivé
		txtDepart = new JTextField(10);
		txtArrival = new JTextField(10);

		mainPanel.add(new JLabel("Départ: "));
		mainPanel.add(txtDepart);
		mainPanel.add(new JLabel("Arrivée: "));
		mainPanel.add(txtArrival);

		JButton btnValid = new JButton("Valider");
		btnValid.addActionListener(new ValidActionListener());
		mainPanel.add(btnValid, "wrap");

		// etiquette d'affichage d'une erreur de saisie
		lblError = new JLabel(" ");
		lblError.setForeground(Color.RED);
		mainPanel.add(lblError, "span, wrap 15");

		// panneau avec les chemins
		graphPane = new GraphPane(graph);
		mainPanel.add(graphPane, "align center, span");

		mainPanel
				.add(new JLabel(
						"<html><i>Source: https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra</i></html>"),
						"span");

		// caracteristique de la fenetre
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Ecouter les action sur le bouton valider.
	 */
	private class ValidActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// effacer la barre d'erreur
			showError(" ");

			// retrouver les points de départ et d'arrivée
			String strDep = txtDepart.getText().toLowerCase().trim();
			String strArr = txtArrival.getText().toLowerCase().trim();
			GraphSummit dep = graph.getSummit(strDep);
			GraphSummit arr = graph.getSummit(strArr);

			// verifier les points
			if (dep == null) {
				showError("Point de départ invalide: '" + strDep + "'");
				return;
			}
			if (arr == null) {
				showError("Point d'arrivée invalide: '" + strArr + "'");
				return;
			}

			// récupérer le plus court chemin
			GraphPath shorterPath = graph.shorterPath(strDep, strArr);

			// le dessiner
			graphPane.setShorterPath(shorterPath);
			graphPane.repaint();
		}
	}

	/**
	 * Afficher un message d'erreur dans le label prévu a cet effet
	 * 
	 * @param text
	 */
	private void showError(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				lblError.setText(text);
				lblError.revalidate();
				lblError.repaint();
			}
		});
	}

	/**
	 * Panneau d'affichage du graphe. Le fond (les branches noires) est une
	 * image PNG, le reste est déssiné dynamiquement.
	 * 
	 * @author remipassmoilesel
	 *
	 */
	private class GraphPane extends JPanel {

		private int circleSize;
		private GraphPath shorterPath;
		private int size;
		private Font font;
		private int offset;

		public GraphPane(Graph graph) {
			shorterPath = null;
			circleSize = 20;
			size = 375;
			font = new Font(Font.DIALOG, Font.PLAIN, 17);
			offset = -4;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;

			Utils.applyQualityRenderingHints(g2d);

			// dessiner le fond
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke(4));

			ArrayList<String[]> aretes = graph.getAllAretes();
			for (String[] n : aretes) {
				Point p1 = graph.getOrCreate(n[0]).getPixelPosition();
				Point p2 = graph.getOrCreate(n[1]).getPixelPosition();

				g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
			}

			// dessiner le chemin le plus court
			if (shorterPath != null) {

				g2d.setStroke(new BasicStroke(10));
				g2d.setColor(Color.GREEN);

				ArrayList<GraphSummit> summits = shorterPath.getSummits();

				GraphSummit last = summits.remove(0);

				for (GraphSummit suiv : summits) {
					Point p1 = last.getPixelPosition();
					Point p2 = suiv.getPixelPosition();

					g2d.drawLine(p1.x, p1.y, p2.x, p2.y);

					last = suiv;
				}
			}

			// dessiner les sommets
			g2d.setColor(Color.BLUE);
			Iterator<GraphSummit> it = graph.getAllSummits().values()
					.iterator();
			while (it.hasNext()) {
				GraphSummit sum = it.next();
				Point p = sum.getPixelPosition();

				p.x -= circleSize / 2;
				p.y -= circleSize / 2;

				g2d.fillOval(p.x, p.y, circleSize, circleSize);

				g2d.setFont(font);

				g2d.drawString(sum.getName(), p.x + offset, p.y + offset);

			}

		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(size, size);
		}

		public void setShorterPath(GraphPath shorterPath) {
			this.shorterPath = shorterPath;
		}

	}

}
