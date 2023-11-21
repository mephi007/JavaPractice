package com.example.javapractice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class leetCode1116 {

    public static void main(String[] args) {
        ZeroEvenOdd resource = new ZeroEvenOdd(5);
        IntConsumer printNumber = (number) -> System.out.print(number);
        Thread t1 = new Thread(() -> {
            try {
                resource.zero(printNumber);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                resource.even(printNumber);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                resource.odd(printNumber);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
    
}

class ZeroEvenOdd {
    private int n;
    // private int count;
    private Lock lock;
    private boolean invokeZero;
    private boolean invokeEven;
    private boolean invokeOdd;
    private Condition zero;
    private Condition even;
    private Condition odd;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
        // this.count = 0;
        this.invokeZero = true;
        this.invokeEven = false;
        this.invokeOdd = false;
        lock = new ReentrantLock();
        this.zero = lock.newCondition();
        this.even = lock.newCondition();
        this.odd = lock.newCondition();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        
        for(int i = 1; i<=this.n; i++){
            lock.lock();
            try{
                // if(count > n) return;
                while(!this.invokeZero){
                    zero.await();
                }
                printNumber.accept(0);
                this.invokeZero = false;
                // this.count++;
                if(i%2 == 0){
                        this.invokeEven = true;
                        even.signal();
                    }
                else {
                        this.invokeOdd = true;
                        odd.signal();
                    }
        
            }finally{
                lock.unlock();
            }
        }    
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        
        for(int i = 2; i<=this.n; i +=2){
            lock.lock();
            try{
                // if(count > n) return;
                while(!this.invokeEven){
                    even.await();
                }
                printNumber.accept(i);
                this.invokeZero = true;
                this.invokeEven = false;
                // this.count++;
                zero.signal();
            }finally{
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=this.n; i += 2){
            lock.lock();
            try{
                // if(count > n) return;
                while(!this.invokeOdd){
                    odd.await();
                }
                printNumber.accept(i);
                this.invokeZero = true;
                this.invokeOdd = false;
                // this.count++;
                zero.signal();
            }finally{
                lock.unlock();
            }
        }
    }
}
