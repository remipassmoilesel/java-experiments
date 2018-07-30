package org.prog2.huffman;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HuffmanCode<E> implements Serializable {

	/** Les élements à coder */
	private List<E> elements;

	/** Buffer pour écriture d'objets */
	private transient StringBuffer buffer;

	/** Racine de l'arbre */
	private AbstractNode<E> tree;

	public HuffmanCode(List<E> elements) {
		this.elements = elements;
		Map<E, Integer> occ = countOccurences();
		buildTree(occ);
	}

	/**
	 * Compter le nombre d'occurences de chaque objet dans la liste d'element du
	 * code
	 * 
	 * @return
	 */
	public HashMap<E, Integer> countOccurences() {

		HashMap<E, Integer> output = new HashMap<E, Integer>();
		for (E e : elements) {
			Integer occ = output.get(e);
			if (occ == null) {
				output.put(e, 1);
			} else {
				output.put(e, ++occ);
			}
		}

		return output;
	}

	public List<E> getElements() {
		return elements;
	}

	private void buildTree(Map<E, Integer> occ) {

		// liste de toutes les feuilles triées
		SortedList<AbstractNode<E>> nodes = new SortedList<AbstractNode<E>>();

		// créer une liste triée de feuilles
		Iterator<E> it = occ.keySet().iterator();
		while (it.hasNext()) {
			E k = it.next();
			Integer v = occ.get(k);
			nodes.add(new Leaf<E>(k, v));
		}

		// trier les feuilles et créer des branches
		while (nodes.size() >= 2) {
			AbstractNode<E> l1 = nodes.remove(0);
			AbstractNode<E> l2 = nodes.remove(0);
			nodes.add(new BinaryNode<E>(l1, l2));
		}

		// conserver la référence à la racine
		tree = nodes.get(0);
	}

	public void showCodedElements() {

		// itérer et afficher le dictionnaire
		Map<E, String> dict = tree.getDictionnary();

		System.out.println("Dictionnaire: ");

		Iterator<E> it = dict.keySet().iterator();
		while (it.hasNext()) {
			E k = it.next();
			String v = dict.get(k);

			System.out.println(v);
			System.out.println(k);
		}

		// iterer et afficher les elements
		System.out.println("Elements: ");
		for (E elmt : elements) {
			System.out.println(dict.get(elmt));
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {

		// itérer et ecrire l'arbre
		Map<E, String> dict = tree.getDictionnary();
		out.writeObject(tree);

		// iterer et ecrire les elements
		for (E elmt : elements) {
			writeBinaryString(dict.get(elmt), out);
		}

		endOfBinaryWriting(out);
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {

		// extraire l'arbre
		tree = (AbstractNode<E>) in.readObject();

		elements = new ArrayList<E>();

		HuffmanIterator hi = new HuffmanIterator(in);
		while (hi.hasNext()) {
			E n = tree.decode(hi);
			if (n == null) {
				break;
			}
			elements.add(n);
		}

	}

	/**
	 * Source: document de TP
	 * 
	 * @param s
	 * @param out
	 * @throws IOException
	 */
	private void writeBinaryString(String s, ObjectOutputStream out)
			throws IOException {

		// vérifier le format de la liste
		if (!s.matches("^[0-1]*$")) {
			throw new IOException("La chaine est invalide");
		}

		// créer le buffer si necessaire
		if (buffer == null) {
			buffer = new StringBuffer();
		}

		buffer.append(s);

		while (buffer.length() > 7) {
			int b = Integer.parseInt(buffer.substring(0, 8), 2);
			out.write(b);
			buffer.delete(0, 8);
		}
	}

	/**
	 * Source: document de TP
	 * 
	 * @param out
	 * @throws IOException
	 */
	private void endOfBinaryWriting(ObjectOutputStream out) throws IOException {
		if (buffer != null && buffer.length() > 0) {
			for (int i = buffer.length(); i < 8; i++)
				buffer.append('0');
			int b = Integer.parseInt(buffer.substring(0, 8), 2);
			out.write(b);
			buffer = null;
		}
	}

	public AbstractNode<E> getTree() {
		return tree;
	}

	/**
	 * Source: document de TP
	 * 
	 */
	private static final class HuffmanIterator implements Iterator<Boolean> {

		private byte current;
		private byte readed = 8;
		private InputStream is;

		HuffmanIterator(InputStream is) {
			this.is = is;
		}

		public boolean hasNext() {
			if (readed == 8) {
				int lu = -1;
				try {
					if ((lu = is.read()) == -1)
						return false;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				current = (byte) lu;
				readed = 0;
			}
			return true;
		}

		public Boolean next() {
			hasNext();
			boolean b;
			if ((current & 128) == 128)
				b = true;
			else
				b = false;
			current <<= 1;
			readed++;
			return b;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
