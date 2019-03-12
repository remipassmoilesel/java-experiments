package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.collection.List;
import lombok.val;
import org.junit.Test;

public class GroupByTest {

    @Test
    public void groupBy() {
        val map = List.rangeClosed(0, 10).groupBy(i -> i % 2);
        System.out.println(map);

        val map2 = List.rangeClosed(0, 10).groupBy(i -> i % 2).toList();
        System.out.println(map2);
    }
}
