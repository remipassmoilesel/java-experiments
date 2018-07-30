package org.prog2.huffman;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class AbstractNode<E> implements Comparable<AbstractNode<E>>,
		Serializable {

	private int occurenceNumber;

	public AbstractNode(int occ) {
		occurenceNumber = occ;
	}

	public int getOcurenceNumber() {
		return occurenceNumber;
	}

	@Override
	public int compareTo(AbstractNode<E> o) {

		if (this.getOcurenceNumber() == o.getOcurenceNumber()) {
			return 0;
		}

		return this.getOcurenceNumber() < o.getOcurenceNumber() ? -1 : +1;
		
	}

	public Map<E, String> getDictionnary() {
		
		// créer un dictionnaire
		Map<E, String> dict = new HashMap<E, String>();
		
		// remplir le dictionnaire  
		getDictionnary(dict, new StringBuffer());
		
		return dict;
	}

	protected abstract void getDictionnary(Map<E, String> dict,
			StringBuffer buf);

	public abstract E decode(Iterator<Boolean> codes);

	public void setOccurenceNumber(int occurenceNumber) {
		this.occurenceNumber = occurenceNumber;
	}
	
}
