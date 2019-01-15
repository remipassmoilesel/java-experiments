package org.prog2.coding;

public class NumCoder extends Coder {

	private String key;
	private int indexOnKey;

	public NumCoder(String key) {
		resetKey(key);
	}

	public void resetKey(String key) {
		this.indexOnKey = 0;
		this.key = key;
	}

	@Override
	public char codeChar(int c) {
		return (char) (c + getNextKeyCar());
	}

	@Override
	public char decodeChar(int c) {
		return (char) (c - getNextKeyCar());
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
