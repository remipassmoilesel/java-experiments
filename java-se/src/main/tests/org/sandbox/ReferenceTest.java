package org.sandbox;

import org.junit.Assert;
import org.junit.Test;

public class ReferenceTest {

	/**
	 * Vérifier les passages de références sur des wrapper de types
	 */
	@Test
	public void testWrappers() {

		float a = 18;
		Float b = a;

		b = 20f;

		Assert.assertNotSame(new Float(a), b);

		System.out.println(a);
		System.out.println(b);

	}

}
