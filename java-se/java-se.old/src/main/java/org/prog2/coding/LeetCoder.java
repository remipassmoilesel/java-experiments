package org.prog2.coding;


public class LeetCoder extends Coder {

	@Override
	public char codeChar(int c) {

		if (c == 'e') {
			return '3';
		}

		return (char) c;

	}

	@Override
	public char decodeChar(int c) {

		if (c == '3') {
			return 'e';
		}

		return (char) c;
	}

}
