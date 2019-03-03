package org.remipassmoilesel.lightplayground.vavr;

import org.junit.Test;

public class TryTest {

    public static class FakeHttpClient {
        public String get(String url) {
            if (url.contains("nowhere")) {
                throw new RuntimeException("Not found: " + url);
            }
            return "Response body";
        }
    }

    final FakeHttpClient client = new FakeHttpClient();

    @Test(expected = Exception.class)
    public void shouldThrow() {
        client.get("http://nowhere");
    }

}
