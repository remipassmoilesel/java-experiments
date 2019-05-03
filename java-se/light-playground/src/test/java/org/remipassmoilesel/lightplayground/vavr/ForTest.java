package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.control.Try;
import lombok.val;
import org.junit.Test;

import static io.vavr.API.For;

public class ForTest {

    @Test
    public void forComprehensionSucceed() {
        val divide = Function2.of((Integer a, Integer b) -> Try.of(() -> a / b).toEither());

        val result = For(
                divide.apply(30, 6),
                divide.apply(30, 7)
        )
                .yield((res1, res2) -> Tuple.of(res1, res2))
                .toEither(new Exception("One operation failed"));

        System.out.println(result);
    }

    @Test
    public void forComprehensionFailed(){
        val divide = Function2.of((Integer a, Integer b) -> Try.of(() -> a / b).toEither());

        val result = For(
                divide.apply(5, 0),
                divide.apply(5, 7)
        )
                .yield((res1, res2) -> Tuple.of(res1, res2))
                .toEither(new Exception("One operation failed"));

        System.out.println(result);
    }
}
