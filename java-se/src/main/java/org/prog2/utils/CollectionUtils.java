package org.prog2.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class CollectionUtils {

	public static void main(String[] args) {

		// liste non triée
		ArrayList<Element> list = new ArrayList<Element>();
		list.add(new Element(-1, "pierre"));
		list.add(new Element(25, "paul"));
		list.add(new Element(6, "jacques"));

		System.out.println("Non-sorted: ");
		System.out.println(list);

		// tri de la liste
		Collections.sort(list);

		System.out.println("Sorted: ");
		System.out.println(list);

		// liste triée
		TreeSet<Element> sortedSet = new TreeSet<Element>();
		sortedSet.add(new Element(-1, "pierre"));
		sortedSet.add(new Element(25, "paul"));
		sortedSet.add(new Element(6, "jacques"));

		System.out.println(sortedSet.getClass().getName() + ": ");
		System.out.println(sortedSet);

		// conversion tableau / str
		System.out.println(Arrays.deepToString(list.toArray(new Element[list
				.size()])));

	}

	/**
	 * Retourner une liste non modifiable
	 */
	public void getImmutableList() {
		// Collections.......
	}

	/**
	 * Element de liste comparable
	 * 
	 * @author remipassmoilesel
	 *
	 */
	public static class Element implements Comparable<Element> {

		private int coeff;
		private String name;

		public Element(int coeff, String name) {
			this.coeff = coeff;
			this.name = name;
		}

		@Override
		public int compareTo(Element o) {
			if (coeff == o.coeff) {
				return 0;
			}

			else {
				return coeff < o.coeff ? -1 : +1;
			}
		}

		@Override
		public String toString() {
			return this.getClass().getSimpleName() + " [" + coeff + ", " + name
					+ "]";
		}
	}

}
