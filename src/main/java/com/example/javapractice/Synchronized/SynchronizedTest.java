package com.example.javapractice.Synchronized;

public class SynchronizedTest {
    public static void main(String[] args) {
        SharedResource s1 = new SharedResource();
        Thread t1 = new Thread(() -> s1.producer());
        SharedResource s2 = new SharedResource();
        Thread t2 = new Thread(() -> s2.producer());
        t1.start();
        t2.start();
    }
}
