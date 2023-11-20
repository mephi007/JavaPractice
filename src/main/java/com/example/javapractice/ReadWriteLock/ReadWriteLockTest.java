package com.example.javapractice.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Thread t1 = new Thread(() -> {
            resource.producer(lock);
        });

        Thread t2 = new Thread(() -> {
            resource.producer(lock);
        });

        Thread t3 = new Thread(() -> {
            resource.consume(lock);
        });

        t1.start();
        t2.start();
        t3.start();
    }
    
}
