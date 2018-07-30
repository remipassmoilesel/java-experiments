package org.prog2.huffman;

import java.util.Iterator;
import java.util.Map;

public class BinaryNode<E> extends AbstractNode<E> {

	private AbstractNode<E> left, right;

	public BinaryNode(AbstractNode<E> left, AbstractNode<E> right) {
		super(left.getOcurenceNumber() + right.getOcurenceNumber());
		this.left = left;
		this.right = right;
	}

	@Override
	protected void getDictionnary(Map<E, String> dict, StringBuffer buf) {
		
		// copier le buffer
		StringBuffer buf2 = new StringBuffer(buf);

		// codage branche gauche
		buf.append("0");
		left.getDictionnary(dict, buf);

		// codage branche droite, avec une copie du buffer
		buf2.append("1");
		right.getDictionnary(dict, buf2);
	}

	@Override
	public E decode(Iterator<Boolean> codes) {

		if (codes.hasNext()) {

			if (codes.next()) {
				return right.decode(codes);
			}

			else {
				return left.decode(codes);
			}

		}

		else
			return null;
	}
}
