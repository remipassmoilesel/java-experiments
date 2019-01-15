package org.rxjava.misc;

import io.reactivex.Observable;
import org.rxjava.fake_comm.FakeComm;
import org.rxjava.fake_comm.Message;

import java.util.concurrent.TimeUnit;

public class Timer {

    public static void main(String[] args) {
        Observable.interval(2, TimeUnit.SECONDS)
                .map((tick) -> {
                    System.out.println(tick);
                    return tick;
                })
                .subscribe();
    }

    public static void flatMapSingle(String[] args) {
        FakeComm comm = FakeComm.getFakeClient();

        Observable.interval(2, TimeUnit.SECONDS)
                .flatMapSingle((tick) -> comm.request("test", new Message("message " + tick)))
                .subscribe((message) -> {
                    System.out.println(message);
                });
    }
}
