package com.example.javapractice;

public class Multithreading implements Runnable {

    @Override
    public void run(){
        System.out.println("code executed by thread --> " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Going inside the main method --> " + Thread.currentThread().getName());
        Multithreading t = new Multithreading();
        Thread thread = new Thread(t);
        thread.start();

        Thread  exThread = new ExtendingThread();
        exThread.start();

        MonitorLockExample me = new MonitorLockExample();
        MonitorThread1Runnable meRunnable = new MonitorThread1Runnable(me);
        Thread t1 = new Thread(meRunnable);
        Thread t2 = new Thread(() -> {me.task2();});
        Thread t3 = new Thread(() -> {me.task3();});
        t1.start();
        t2.start();
        t3.start();
    }
    
}
