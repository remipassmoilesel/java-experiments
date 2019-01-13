package org.rxjava.misc;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

// from https://www.infoq.com/articles/rxjava2-by-example

public class RxLists {

    static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    public static void main(String[] args) {
        Observable.fromIterable(words)
                .flatMap(word -> Observable.fromArray(word.split("")))
                .distinct()
                .sorted()
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string))
                .subscribe(System.out::println);
    }
}
