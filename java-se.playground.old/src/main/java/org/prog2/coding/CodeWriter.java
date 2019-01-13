package org.prog2.coding;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CodeWriter extends FilterWriter {

	private Coder coder;

	protected CodeWriter(Writer out, Coder coder) {
		super(out);

		this.coder = coder;
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		super.write(coder.codeChars(str.toCharArray()), off, len);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		super.write(coder.codeChars(cbuf), off, len);
	}

}
