package org.prog2.graphs;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.SwingUtilities;

/**
 * Implémentation de l'algorithme de Djikstra qui permet de trouver le chemin le
 * plus court entre plusieurs points.
 * 
 * @author remipassmoilesel
 *
 */
public class Launcher {

	public static void main(String[] args) {
		// simpleExample();
		showGui();
	}

	private static void showGui() {

		// créer un graphe
		final Graph graph = new Graph();

		// determiner les arretes
		graph.addArete("a", "b", 85);
		graph.addArete("b", "f", 80);
		graph.addArete("f", "i", 250);
		graph.addArete("i", "j", 84);
		graph.addArete("a", "c", 217);
		graph.addArete("c", "g", 186);
		graph.addArete("c", "h", 103);
		graph.addArete("d", "h", 183);
		graph.addArete("h", "j", 167);
		graph.addArete("a", "e", 173);
		graph.addArete("e", "j", 502);

		// determiner les positions visuelles des sommets
		// les positions ne sont pas ULC et seront transformés
		// ci-dessous
		HashMap<String, Point> positions = new HashMap<String, Point>();
		int size = 375;
		positions.put("a", new Point(134, 349));
		positions.put("b", new Point(14, 271));
		positions.put("c", new Point(134, 270));
		positions.put("d", new Point(246, 255));
		positions.put("e", new Point(365, 240));
		positions.put("f", new Point(11, 175));
		positions.put("g", new Point(109, 177));
		positions.put("h", new Point(226, 179));
		positions.put("i", new Point(115, 96));
		positions.put("j", new Point(230, 29));

		HashMap<String, GraphSummit> summits = graph.getAllSummits();

		Iterator<String> it = positions.keySet().iterator();

		while (it.hasNext()) {
			// nom de la position à appliquer
			String n = it.next();

			// position à appliquer
			Point p = positions.get(n);
			GraphSummit s = summits.get(n);

			// position corrigée
			s.setPixelPosition(p.x, size - p.y);
		}

		// créer et montrer la fenêtre
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// création et affichage du GUI
				GraphGui pg = new GraphGui(graph);
				pg.setVisible(true);
			}
		});
	}

	public static void simpleExample() {

		Graph graph = new Graph();

		graph.addArete("a", "b", 85);
		graph.addArete("b", "f", 80);
		graph.addArete("f", "i", 250);
		graph.addArete("i", "j", 84);

		graph.addArete("a", "c", 217);
		graph.addArete("c", "g", 186);
		graph.addArete("c", "h", 103);
		graph.addArete("d", "h", 183);
		graph.addArete("h", "j", 167);

		graph.addArete("a", "e", 173);
		graph.addArete("e", "j", 502);

		System.out.println("Chemin le plus court: ");
		System.out.println(graph.shorterPath("a", "j"));

	}

}
