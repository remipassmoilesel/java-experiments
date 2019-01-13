package org.prog2.graphs;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;

public class GraphSummit {

	/** Nom du sommet */
	private String name;

	/** Voisins du sommet */
	private HashMap<GraphSummit, Integer> neighbours;

	/** Position du sommet en pixel sur le canvas */
	private Point pixelPosition;

	public GraphSummit(String name) {
		this.name = name;
		this.neighbours = new HashMap<GraphSummit, Integer>();

		this.pixelPosition = new Point();
	}

	public void addNeighbours(GraphSummit summit, int distance) {
		neighbours.put(summit, distance);
	}

	/**
	 * Renvoie vrai si le sommet passé en pramaètre est un voisin.
	 * 
	 * @param summit
	 * @return
	 */
	public boolean isNeighbours(GraphSummit summit) {
		return getDistanceFrom(summit) != -1;
	}

	/**
	 * Renvoi la distance avec le sommet passé en parametre ou -1 si le sommet
	 * n'est pas un voisin
	 * 
	 * @param summit
	 */
	public int getDistanceFrom(GraphSummit summit) {
		Integer dist = neighbours.get(summit);
		return dist != null ? dist : -1;
	}

	public Iterator<GraphSummit> getNeightboursIterator() {
		return neighbours.keySet().iterator();
	}

	public String getName() {
		return name;
	}

	public Point getPixelPosition() {
		return new Point(pixelPosition);
	}

	public void setPixelPosition(int x, int y) {
		this.pixelPosition = new Point(x, y);
	}

	public void setPixelPosition(Point pixelPosition) {
		this.pixelPosition = pixelPosition;
	}

	@Override
	public String toString() {
		return "GraphSummit [name=" + name + ", neighbours="
				+ neighbours.size() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphSummit other = (GraphSummit) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
