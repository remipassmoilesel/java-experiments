
package org.sample;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class VavrBenchmark {

    public static final int ITEMS = 5000;
    public static final int ITERATIONS = 5;
    public static final int TIME = 1000;

    @Benchmark
    @Warmup(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    @Measurement(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    public void arrays() {

        int[] items = new int[ITEMS];
        for (int i = 0; i < ITEMS; i++) {
            items[i] = i;
        }

        for (int i = 0; i < ITEMS; i++) {
            int[] copy = items.clone();
        }

    }

    @Benchmark
    @Warmup(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    @Measurement(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    public void lists() {

        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < ITEMS; i++) {
            items.add(i);
        }

        for (int i = 0; i < ITEMS; i++) {
            ArrayList copy = (ArrayList) items.clone();
        }

    }

    @Benchmark
    @Warmup(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    @Measurement(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    public void vavrLists() {

        List<Integer> list = Stream.from(0).take(ITEMS).map(i -> i).toList();

        for (int i = 0; i < ITEMS; i++) {
            List<Integer> copy = list.toList();
        }

    }

    @Benchmark
    @Warmup(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    @Measurement(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    public void vavrListToJavaList() {

        List<Integer> list = Stream.from(0).take(ITEMS).map(i -> i).toList();

        for (int i = 0; i < ITEMS; i++) {
            java.util.List<Integer> copy = list.toJavaList();
        }

    }

    @Benchmark
    @Warmup(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    @Measurement(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    public void vavrListAsJava() {

        List<Integer> list = Stream.from(0).take(ITEMS).map(i -> i).toList();

        for (int i = 0; i < ITEMS; i++) {
            java.util.List<Integer> copy = list.asJava();
        }

    }

    @Benchmark
    @Warmup(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    @Measurement(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    public void javaStream() {

        java.util.List<Integer> list = IntStream.range(0, ITEMS).boxed().collect(Collectors.toList());
        java.util.List<Integer> list2 = list.stream().map(i -> i * 2).collect(Collectors.toList());

    }

    @Benchmark
    @Warmup(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    @Measurement(iterations = ITERATIONS, time = TIME, timeUnit = MILLISECONDS)
    public void vavrStreamFromJavaList() {

        java.util.List<Integer> list = IntStream.range(0, ITEMS).boxed().collect(Collectors.toList());
        List<Integer> list2 = Stream.ofAll(list).map(i -> i * 2).toList();

    }

}
