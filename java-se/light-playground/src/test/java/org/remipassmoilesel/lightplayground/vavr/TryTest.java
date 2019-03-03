package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.control.Try;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TryTest {

    public static class HttpClient {
        public String get(String url) {
            if (url.contains("nowhere")) {
                throw new RuntimeException("Not found: " + url);
            }
            return "Response body";
        }
    }

    @Test(expected = Exception.class)
    public void shouldThrow() {
        final var client = new HttpClient();
        client.get("http://nowhere");
    }

    @Test
    public void failedTry() {
        final var client = new HttpClient();
        Try<String> res = Try.ofSupplier(() -> client.get("http://nowhere"));

        assertThat(res.isFailure(), is(true));
    }

}
