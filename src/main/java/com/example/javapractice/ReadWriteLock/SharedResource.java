package com.example.javapractice.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {
    boolean isAvailable = false;
    public void producer(ReadWriteLock lock){
        try{
            lock.readLock().lock();
            System.out.println("read lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(8000);
        }catch(Exception e){

        }finally{
            lock.readLock().unlock();
            System.out.println("read lock release by: " + Thread.currentThread().getName());
        }
    }

    public void consume(ReadWriteLock lock){
        try{
            lock.writeLock().lock();
            System.out.println("write lock acquired by: " + Thread.currentThread().getName());
            isAvailable = false;
        }catch(Exception e){

        }finally{
            lock.writeLock().lock();
            System.out.println("write lock release by: " + Thread.currentThread().getName());
        }
    }
}
