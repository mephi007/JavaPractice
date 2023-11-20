package com.example.javapractice.RentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        SharedResource s1 = new SharedResource();
        SharedResource s2 = new SharedResource();
        Thread t1 = new Thread(() -> s1.producer(lock));
        Thread t2 = new Thread(() -> s2.producer(lock));
        t1.start();
        t2.start();
    }
}
