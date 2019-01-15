package org.prog2.coding;

public abstract class Coder {

	public abstract char codeChar(int c);

	public abstract char decodeChar(int c);

	public char[] codeChars(char[] crs) {
		char[] output = new char[crs.length];

		for (int i = 0; i < crs.length; i++) {
			output[i] = codeChar(crs[i]);
		}

		return output;
	}

	public char[] decodeChars(char[] crs) {

		for (int i = 0; i < crs.length; i++) {
			crs[i] = decodeChar(crs[i]);
		}

		return crs;
	}

	public void reset() {

	}

}
