package org.prog2.huffman;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class SortedList<E> extends LinkedList<E> {

	@Override
	public boolean add(E e) {

		if (e instanceof Comparable<?> == false) {
			throw new IllegalArgumentException("Object must be comparable: "
					+ e);
		}

		Comparable<E> ce = (Comparable<E>) e;
		
		// parcourir les elements de la liste jusqu'à trouver un element plus grand
		int j = 0;
		for (E elmt : this) {
			
			if(ce.compareTo(elmt) < 0){
				break;
			}
			
			j++;
		}

		super.add(j, e);
		
		// retourne toujours vrai, en accord avec Collection<E>.add()
		return true;
	}
	
	@Deprecated
	@Override
	public void add(int index, E element) {
		throw new IllegalStateException("Not allowed");
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new IllegalStateException("Not allowed");
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new IllegalStateException("Not allowed");
	}

}
