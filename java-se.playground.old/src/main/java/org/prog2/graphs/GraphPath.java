package org.prog2.graphs;

import java.util.ArrayList;
import java.util.Iterator;

public class GraphPath implements Iterable<GraphSummit>, Comparable<GraphPath> {

	private ArrayList<GraphSummit> summits;

	public GraphPath() {
		summits = new ArrayList<GraphSummit>();
	}

	public GraphPath(GraphSummit begin) {
		this();
		addSummit(begin);
	}

	protected GraphPath(GraphPath basePath, GraphSummit next) {

		this();

		// ajouter les sommets
		for (GraphSummit sum : basePath.getSummits()) {
			addSummit(sum);
		}

		addSummit(next);

	}

	/**
	 * Ajouter un sommet au chemin
	 * 
	 * @param sum
	 * @throws IllegalSummitException
	 */
	public void addSummit(GraphSummit sum) {

		// si la liste n'est pas vide, verifier que le sommet est bien un voisin
		if (summits.size() > 0) {
			if (getEndSummit().isNeighbours(sum) == false) {
				throw new IllegalStateException("Not a neighbour: " + sum);
			}
		}
		summits.add(sum);
	}

	public ArrayList<GraphSummit> getSummits() {
		return new ArrayList<GraphSummit>(summits);
	}

	/**
	 * Retourner le dernier sommet du chemin
	 * 
	 * @return
	 */
	public GraphSummit getEndSummit() {

		if (summits.size() < 1) {
			throw new IndexOutOfBoundsException("Path is empty: "
					+ summits.size());
		}

		return summits.get(summits.size() - 1);
	}

	/**
	 * Retourne une collection qui est la concatenation du chemin actuel et des
	 * voisins du dernier chemin.
	 * 
	 * @return
	 */
	public ArrayList<GraphPath> extend() {

		ArrayList<GraphPath> output = new ArrayList<GraphPath>();

		// créer un chemin pour chaque sommet
		Iterator<GraphSummit> it = getEndSummit().getNeightboursIterator();
		while (it.hasNext()) {
			GraphSummit sum = it.next();

			// créer le chemin uniquement si le noeud ne fais pas parti du
			// chemin
			if (summits.contains(sum) == false) {
				output.add(new GraphPath(this, sum));
			}
		}

		return output;

	}

	@Override
	public Iterator<GraphSummit> iterator() {
		return summits.iterator();
	}

	/**
	 * Retourne une valeur positive si le chemin en paramètre est plus petit.
	 */
	@Override
	public int compareTo(GraphPath other) {

		if (other.getDistance() != getDistance()) {
			return other.getDistance() > getDistance() ? -1 : +1;
		}

		else if (other.summits.size() != summits.size()) {
			return other.summits.size() > summits.size() ? -1 : +1;
		}

		else {

			StringBuilder p = new StringBuilder();
			for (GraphSummit sum : summits) {
				p.append(sum.getName());
			}

			StringBuilder po = new StringBuilder();
			for (GraphSummit sum : other.summits) {
				po.append(sum.getName());
			}

			return po.toString().compareTo(p.toString());
		}

	}

	/**
	 * Calcule la distance entre le premier et le dernier sommet.
	 * 
	 * @return
	 */
	public int getDistance() {

		int output = 0;

		if (summits.size() <= 1) {
			return output;
		}

		Iterator<GraphSummit> it = summits.iterator();
		GraphSummit last = it.next();
		while (it.hasNext()) {
			GraphSummit present = it.next();
			output += present.getDistanceFrom(last);
			last = present;
		}

		return output;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof GraphPath == false) {
			return false;
		}

		return compareTo((GraphPath) obj) == 0;
	}

	@Override
	public String toString() {
		return "GraphPath [distance=" + getDistance() + ", summits="
				+ summits.size() + " - " + summits + "]";
	}
}
