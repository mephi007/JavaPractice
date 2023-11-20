package com.example.javapractice.RentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    boolean isAvailable = false;
    public void producer(ReentrantLock lock){
        try{
            lock.lock();
            System.out.println(String.format("Lock acquired by: %s", Thread.currentThread().getName()));
            isAvailable = true;
            Thread.sleep(4000);
        }catch(Exception e){ }
        finally{
            lock.unlock();
            System.out.println(String.format("Lock release by: %s", Thread.currentThread().getName()));
        }
    }
    
}
