package org.rxjava;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.rxjava.fake_comm.FakeComm;
import org.rxjava.fake_comm.FakeCommClient;
import org.rxjava.fake_comm.Message;

public class RxMicroserviceCommTest {

    @Test
    public void fakeClientShouldWorkProperlyBlockingVersion() {
        String testSubject = "test-subject";
        Message testMessage = new Message("test-message");

        FakeComm comm = FakeComm.getFakeClient();
        comm.subscribe(testSubject, (subject, message) -> {
            // return new Message(message.getContent() + "should fail");
            return new Message(message.getContent());
        });
        Single<Message> response = comm.request(testSubject, testMessage);

        TestObserver<Message> observer = response.test();
        response.blockingGet();

        observer.assertComplete();
        observer.assertValue(testMessage);
    }

}
