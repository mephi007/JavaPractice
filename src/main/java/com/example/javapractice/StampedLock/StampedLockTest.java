package com.example.javapractice.StampedLock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        StampedLock lock = new StampedLock();

        Thread t1 = new Thread(() -> {
            resource.producer(lock);
        });

        Thread t2 = new Thread(() -> {
            resource.producer(lock);
        });

        Thread t3 = new Thread(() -> {
            resource.consumer(lock);
        });

        t1.start();
        t2.start();
        t3.start();

        //optimistic concurrency

        Thread t4 = new Thread(() -> {
            resource.OptimisticProducer(lock);
        });

        Thread t5 = new Thread(() -> {
            resource.OptimisticProducer(lock);
        });

        Thread t6 = new Thread(() -> {
            resource.OptimisticConsumer(lock);
        });

        t4.start();
        t5.start();
        t6.start();
    }
}
