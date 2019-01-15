package org.learn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.learn.message.ServiceMessage;

public class LearnTests {

	@Test
	public void testServiceMessageParsing() {

		String msg = "//hello;world=hello;world";
		ServiceMessage parsed = ServiceMessage.parseServiceMessage(msg);

		assertNotNull(parsed);
		assertEquals(parsed.getMessageID(), "hello");
		assertEquals(parsed.getArgs().size(), 2);

	}
}
