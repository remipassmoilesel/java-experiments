package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.collection.List;
import lombok.val;
import org.junit.Test;

public class ListsTest {

    @Test
    public void listsToJava() {
        val list = List.of(1, 2, 3).asJava();
        val list2 = List.of(1, 2, 3).toJavaList(); // less performant
    }

}
