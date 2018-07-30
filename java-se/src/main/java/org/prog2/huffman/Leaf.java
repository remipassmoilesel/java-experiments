package org.prog2.huffman;

import java.util.Iterator;
import java.util.Map;

public class Leaf<E> extends AbstractNode<E> {

	private E element;

	public Leaf(E elem, int occ) {
		super(occ);
		this.element = elem;
	}

	@Override
	protected void getDictionnary(Map<E, String> dict, StringBuffer buf) {
		dict.put(element, buf.toString());
	}

	@Override
	public E decode(Iterator<Boolean> elemsCodes) {
		return element;
	}

}
