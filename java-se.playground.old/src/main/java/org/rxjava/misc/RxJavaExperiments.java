package org.rxjava.misc;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.TestObserver;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.UUID;

public class RxJavaExperiments {

    public static void main(String[] args) throws InterruptedException {
        Disposable disposable = createRandomEmitter()
                .subscribe(System.out::println);

        // disposable.dispose(); stop subscription
    }

    public static Observable<String> createRandomEmitter() {

        String toCount = "5";
        int expectedNumber = 5;
        int throwNumber = 3;

        Observable<String> randomObservable = Observable.create(emitter -> {
            try {
                String random = "";
                int charCount = 0;
                long i = 0;
                while (charCount != expectedNumber) {
                    random = UUID.randomUUID().toString();
                    charCount = StringUtils.countMatches(random, toCount);

                    if(charCount == throwNumber){
//                        throw new Exception("Count is " + charCount);
                    }

                    emitter.onNext(String.format("Generated=%s charCount=%s i=%s", random, charCount, i));
                    i++;
                }
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        return randomObservable;
    }

    @Test
    public void fromCallable() {
        Observable<String> observable = Observable.fromCallable(() -> {
            Thread.sleep(1000);
            return "foo";
        });
        TestObserver<String> to = new TestObserver<>();
        observable.subscribe(to);
        to.assertValues("foo").assertComplete();
    }

}
