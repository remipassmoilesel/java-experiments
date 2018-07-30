package org.rxjava.misc;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

// Example from https://www.infoq.com/articles/Testing-RxJava2

// See also awaitability for asynchronous code testing
// https://github.com/awaitility/awaitility

public class TestObserverExperiments {

    private static final List<String> WORDS = Arrays.asList(
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

    @Test
    public void testUsingTestObserver() {
        // given:
        TestObserver<String> observer = new TestObserver<>();
        Observable<String> observable = Observable.fromIterable(WORDS)
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, index) -> String.format("%2d. %s", index, string));

        // when:
        observable.subscribe(observer);

        // then:
        observer.assertComplete();
        observer.assertNoErrors();
        observer.assertValueCount(9);
        assertThat(observer.values(), hasItem(" 4. fox"));
    }

    @Test
    public void testFailure() {
        // given:
        TestObserver<String> observer = new TestObserver<>();
        Exception exception = new RuntimeException("boom!");

        Observable<String> observable = Observable.fromIterable(WORDS)
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, index) -> String.format("%2d. %s", index, string))
                .concatWith(Observable.error(exception));

        // when:
        observable.subscribe(observer);

        // then:
        observer.assertError(exception);
        observer.assertNotComplete();
    }

    @Test
    public void testOnSeparatedThreadUsingBlockingCall() {
        // given:
        Observable<String> observable = Observable.fromIterable(WORDS)
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, index) -> String.format("%2d. %s", index, string));

        // when:
        Iterable<String> results = observable
                .subscribeOn(Schedulers.computation())
                .blockingIterable(); // block until stream is complete

        // then:
        assertThat(results, notNullValue());
        assertThat(results, hasItem(" 4. fox"));
    }

}
