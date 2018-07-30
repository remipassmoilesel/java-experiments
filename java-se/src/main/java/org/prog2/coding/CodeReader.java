package org.prog2.coding;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CodeReader extends FilterReader {

	private Coder coder;

	public CodeReader(Reader in, Coder coder) {
		super(in);
		this.coder = coder;
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return c != -1 ? coder.decodeChar(c) : -1;
	}

	@Override
	public int read(char[] cbuf) throws IOException {

		int c = super.read(cbuf);

		cbuf = coder.decodeChars(cbuf);

		return c;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {

		// lire le tampon
		int read = super.read(cbuf, off, len);

		// decoder
		cbuf = coder.decodeChars(cbuf);

		return read;

	}
}
