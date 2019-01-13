package org.rxjava.fake_comm;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class FakeComm {

    public static FakeComm getFakeClient() {
        FakeCommClient client = new FakeCommClient();
        return new FakeComm(client);
    }

    private final FakeCommClient client;
    private Scheduler scheduler = Schedulers.computation();

    public FakeComm(FakeCommClient client) {
        this.client = client;
    }

    public Single<Message> request(final String subject, final Message message) {
        return Single.fromCallable(()->{
            return this.client.request(subject, message);
        }).observeOn(this.scheduler);
    }

    public void subscribe(String subject, SyncHandler handler) {
        this.client.subscribe(subject, handler);
    }

}
