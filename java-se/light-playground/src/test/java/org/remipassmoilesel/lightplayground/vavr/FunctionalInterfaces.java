package org.remipassmoilesel.lightplayground.vavr;

import lombok.val;
import org.junit.Test;

import static io.vavr.API.Function;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FunctionalInterfaces {

    @Test
    public void interfaces() {

        val addAll3 = Function((Integer a, Integer b, Integer c) -> a + b + c);
        val addAll8 = Function((Integer a, Integer b, Integer c, Integer d, Integer e, Integer f, Integer g, Integer h) -> a + b + c + d + e + f + g + h);

        assertThat(addAll3.apply(1, 1, 1), equalTo(3));
        assertThat(addAll8.apply(1, 1, 1, 1, 1, 1, 1, 1), equalTo(8));
        assertThat(addAll8.apply(1, 1, 1, 1, 1, 1).apply(1, 1), equalTo(8)); // partial application
    }

}
