package org.prog2.coding;

public class VignereCoder extends Coder {

	private String key;
	private int indexOnKey;

	public VignereCoder(String key) {
		resetKey(key);
	}

	public void resetKey(String key) {
		this.indexOnKey = 0;
		this.key = key;
	}

	@Override
	public char codeChar(int c) {

		if (Character.isAlphabetic(c)) {

			char cref = Character.isLowerCase(c) ? 'a' : 'A';

			c = (c - cref) + (Character.toLowerCase(getNextKeyCar()) - cref);
			c %= 26;
			c += cref;

		}

		return (char) c;

	}

	@Override
	public char decodeChar(int c) {

		if (Character.isAlphabetic(c)) {

			char cref = Character.isLowerCase(c) ? 'a' : 'A';

			c = (c - cref) - (Character.toLowerCase(getNextKeyCar()) - cref);
			c %= 26;
			c += cref;

		}

		return (char) c;
	}

	private int getNextKeyCar() {

		if (indexOnKey >= key.length()) {
			indexOnKey = 0;
		}

		int c = key.charAt(indexOnKey);

		indexOnKey++;

		return c;
	}

	@Override
	public void reset() {
		resetKey(key);
	}

}
