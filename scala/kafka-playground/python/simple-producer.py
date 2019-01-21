#!/usr/bin/env python

# From: https://github.com/dpkp/kafka-python/blob/master/example.py

import threading, logging, time

from kafka import KafkaProducer


class Producer(threading.Thread):
    def __init__(self):
        threading.Thread.__init__(self)
        self.stop_event = threading.Event()

    def stop(self):
        self.stop_event.set()

    def run(self):
        producer = KafkaProducer(bootstrap_servers='localhost:9092')

        while not self.stop_event.is_set():
            print("Sending message from: " + str(threading.get_ident()))
            producer.send('my-topic', b"test")
            producer.send('my-topic', b"\xc2Hola, mundo!")
            time.sleep(0.2)

        producer.close()


def main():
    tasks = [
        Producer(),
        Producer(),
        Producer(),
    ]

    for t in tasks:
        t.start()

    for task in tasks:
        task.join()


if __name__ == "__main__":
    logging.basicConfig(
        format='%(asctime)s.%(msecs)s:%(name)s:%(thread)d:%(levelname)s:%(process)d:%(message)s',
        level=logging.INFO
    )
    main()