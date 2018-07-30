package org.prog2.huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class HuffmanTests {

	@Test
	public void testHuffmanCode() {
		ArrayList<Integer> datas = new ArrayList<Integer>();
		datas.add(5);
		datas.add(5);
		datas.add(10);
		datas.add(10);
		datas.add(10);
		HuffmanCode<Integer> hc = new HuffmanCode<Integer>(datas);

		// tester le comptage des occurences
		HashMap<Integer, Integer> occ = hc.countOccurences();

		Assert.assertEquals((Integer) occ.get(5), (Integer) 2);
		Assert.assertEquals((Integer) occ.get(10), (Integer) 3);
		
		

	}

	@Test
	public void testAbstractNodes() {

		// tester la méthode compareto
		TestNode n1 = new TestNode(20);

		TestNode n2 = new TestNode(50);

		Assert.assertEquals(n1.compareTo(n2), -1);
		Assert.assertEquals(n2.compareTo(n1), +1);
		Assert.assertEquals(n1.compareTo(n1), 0);

	}

	private class TestNode extends AbstractNode<Integer> {

		public TestNode(int occ) {
			super(occ);
		}

		@Override
		protected void getDictionnary(Map<Integer, String> dict,
				StringBuffer buf) {
			// TODO Auto-generated method stub

		}

		@Override
		public Integer decode(Iterator<Boolean> elemsCodes) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Test
	public void testSortedList() {

		// tester la liste ordonnée
		SortedList<Integer> list = new SortedList<Integer>();
		list.add(20);
		list.add(40);
		list.add(550);
		list.add(10);

		Assert.assertEquals("Liste ordonnée: ", (Integer) list.get(0),
				(Integer) 10);

	}

}
