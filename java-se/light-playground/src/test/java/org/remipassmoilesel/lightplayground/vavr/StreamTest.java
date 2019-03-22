package org.remipassmoilesel.lightplayground.vavr;

import io.vavr.Tuple;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import lombok.Builder;
import lombok.Data;
import lombok.val;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StreamTest {

    @Test
    public void fold() {
        val nbr = List.of(1, 2, 3, 4, 5, 6, 7).fold(5, (accumulator, i) -> accumulator + i);
        assertThat(nbr, equalTo(33));
    }

    @Test
    public void foldLeft() {
        val folded = List.of("a", "b", "c").foldLeft("!", (accumulator, x) -> accumulator + x);
        assertThat(folded, equalTo("cba!"));
    }

    @Test
    public void foldRight() {
        val folded = List.of("a", "b", "c").foldRight("!", (x, accumulator) -> accumulator + x);
        assertThat(folded, equalTo("!cba"));
    }

    @Data
    @Builder
    public static class ContainNumber {
        private int number;
    }

    @Test
    public void comparator() {
        val example = List.of(
                ContainNumber.builder().number(3).build(),
                ContainNumber.builder().number(2).build(),
                ContainNumber.builder().number(1).build()
        );

        val sorted = example.sorted(Comparator.comparingInt(ContainNumber::getNumber));
        assertThat(sorted.map(ContainNumber::getNumber).toList(), equalTo(List.of(1, 2, 3)));
    }

    @Test
    public void zip() {
        val list1 = List.of(
                ContainNumber.builder().number(3).build(),
                ContainNumber.builder().number(2).build(),
                ContainNumber.builder().number(1).build()
        );
        val list2 = List.of(
                ContainNumber.builder().number(7).build(),
                ContainNumber.builder().number(6).build(),
                ContainNumber.builder().number(5).build(),
                ContainNumber.builder().number(4).build()
        );

        val zipped1 = list1.zip(list2);
        assertThat(zipped1.map(tuple -> tuple._1.getNumber()).toList(), equalTo(List.of(3, 2, 1)));
        assertThat(zipped1.map(tuple -> tuple._2.getNumber()).toList(), equalTo(List.of(7, 6, 5)));

        val zipped2 = list2.zipWith(list1, (a, b) -> Tuple.of(a, b));
        assertThat(zipped2.size(), equalTo(3));

        val zipped3 = list2.zipWith(list1, (a, b) -> a.getNumber() + b.getNumber());
        assertThat(zipped3, equalTo(List.of(10, 8, 6)));

        val zipped4 = list1.zipAll(list2, ContainNumber.builder().number(-1).build(), ContainNumber.builder().number(-2).build());
        assertThat(zipped4.size(), equalTo(4));
        assertThat(zipped4.map(cn -> cn._1.getNumber()), equalTo(List.of(3, 2, 1, -1)));
        assertThat(zipped4.map(cn -> cn._2.getNumber()), equalTo(List.of(7, 6, 5, 4)));

        val zipped5 = list2.zipWithIndex();
        assertThat(zipped5.map(cn -> cn._1.getNumber()), equalTo(List.of(7, 6, 5, 4)));
        assertThat(zipped5.map(cn -> cn._2), equalTo(List.of(0, 1, 2, 3)));

    }

    @Test
    public void crossProduct() {
        val list1 = List.of(
                ContainNumber.builder().number(3).build(),
                ContainNumber.builder().number(2).build(),
                ContainNumber.builder().number(1).build()
        );
        val cross = list1.crossProduct(5).toList();
        assertThat(cross.size(), equalTo(243));   // 3^5
        System.out.println(cross);
    }

    @Test
    public void combinations() {
        val combinations = List.of(1, 2, 3).combinations();
        assertThat(combinations, equalTo(List.of(List.of(), List.of(1), List.of(2), List.of(3), List.of(1, 2), List.of(1, 3), List.of(2, 3), List.of(1, 2, 3))));
    }

    @Test
    public void grouped() {
        val grouped = Stream.from(0).take(8000).grouped(5);
        assertThat(grouped.size(), equalTo(8000 / 5));
    }

    @Test
    public void intersperse() {
        val grouped = Stream.from(0).take(3).intersperse(5).toList();
        assertThat(grouped, equalTo(List.of(0, 5, 1, 5, 2)));
    }
    
}
