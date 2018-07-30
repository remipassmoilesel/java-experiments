package org.prog2.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import org.Utils;

/**
 * Bilbiothèque de sommets
 * 
 * @author remipassmoilesel
 *
 */
public class Graph {

	private HashMap<String, GraphSummit> summits;
	private ArrayList<String[]> aretes;

	public Graph() {
		this.summits = new HashMap<String, GraphSummit>();
		this.aretes = new ArrayList();
	}

	public GraphSummit getOrCreate(String name) {

		GraphSummit output = summits.get(name);

		if (output == null) {
			GraphSummit newSum = new GraphSummit(name);
			summits.put(name, newSum);
			return newSum;
		}

		else {
			return output;
		}

	}

	public GraphSummit[] addArete(String nameSum1, String nameSum2, int distance) {

		GraphSummit sum1 = getOrCreate(nameSum1);
		GraphSummit sum2 = getOrCreate(nameSum2);

		sum1.addNeighbours(sum2, distance);
		sum2.addNeighbours(sum1, distance);

		aretes.add(new String[] { nameSum1, nameSum2 });

		return new GraphSummit[] { sum1, sum2 };

	}

	public GraphPath shorterPath(String nameSumBegin, String nameSumEnd) {

		GraphSummit sumDep = getOrCreate(nameSumBegin);
		GraphSummit sumArr = getOrCreate(nameSumEnd);

		TreeSet<GraphPath> paths = new TreeSet<GraphPath>();
		paths.add(new GraphPath(sumDep));

		GraphPath p = null;
		while (p == null || p.getEndSummit().equals(sumArr) == false) {

			p = paths.first();
			paths.remove(p);

			paths.addAll(p.extend());

		}

		return p;
	}

	@Override
	public String toString() {
		return "Graph [summits=" + summits + "]";
	}

	public HashMap<String, GraphSummit> getAllSummits() {
		return summits;
	}

	public GraphSummit getSummit(String name) {
		return summits.get(name);
	}

	public ArrayList<String[]> getAllAretes() {
		return aretes;
	}

}
