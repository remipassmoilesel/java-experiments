package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EitherTest {

    public static class FakeService {

        public Either<String, Object> parseInput(String input) {
            if (input.contains("3")) {
                return Either.left("invalid string");
            }
            return Either.right(input.replaceAll("e", "3"));
        }

        public Try<Integer> divide(int a, int b) {
            return Try.of(() -> a / b);
        }

    }

    final FakeService service = new FakeService();

    @Test
    public void eitherTest() {
        assertThat(service.parseInput("Hello world !"), equalTo(Either.right("H3llo world !")));
        assertThat(service.parseInput("I want 3 things"), equalTo(Either.left("invalid string")));
    }

    @Test
    public void tryEitherMap() {
        val by0 = service.divide(1, 0).toEither();
        val by1 = service.divide(1, 1).toEither();

        assertThat(by0.isLeft(), equalTo(true));
        assertThat(by1.isLeft(), equalTo(false));

        System.out.println(by0.get());
    }

}
