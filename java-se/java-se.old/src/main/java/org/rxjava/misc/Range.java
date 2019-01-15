package org.rxjava.misc;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class Range {

    public static void main(String[] args) {
        basicRange1();
        basicRange2();
        basicRange3();
    }

    private static void basicRange3() {
        Observable<String> requests = Observable.range(0, 15)
                .flatMap(number -> Single.just("User " + number).toObservable());
        Iterable<String> replies = requests.blockingIterable();
        replies.forEach(r -> System.out.println(r));
    }

    private static void basicRange2() {
        Observable.range(0, 15)
                .flatMap(number -> Single.just("User " + number).toObservable())
                .subscribe((res) -> {
                    System.out.println(res);
                });
    }

    private static void basicRange1() {
        Observable.range(0, 15)
                .subscribe((number) -> {
                    System.out.println(number);
                });
    }

}
