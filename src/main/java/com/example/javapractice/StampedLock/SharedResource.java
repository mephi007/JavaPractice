package com.example.javapractice.StampedLock;

import java.util.concurrent.locks.StampedLock;

public class SharedResource {
    boolean isAvailable = true;
    int a = 10;

    public void producer(StampedLock lock){
        long stamp = lock.readLock();
        try {
            System.out.println("read lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(6000);
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlockRead(stamp);
            System.out.println("read lock release by: " + Thread.currentThread().getName());
        }
    }

    public void consumer(StampedLock lock){
        long stamp = lock.writeLock();
        try {
            System.out.println("write lock acquired by: " + Thread.currentThread().getName());
            isAvailable = false;
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlockWrite(stamp);
            System.out.println("write lock acquired by: " + Thread.currentThread().getName());
        }
    }
    
    public void OptimisticProducer(StampedLock lock){
        long stamp = lock.tryOptimisticRead();
        try {
            System.out.println("optimistic read lock acquired by: " + Thread.currentThread().getName());
            a = 11;
            Thread.sleep(6000);
            if(lock.validate(stamp)){
                System.out.println("updated a value successfully");
            }else{
                System.out.println("rollback of work as version got changed");
                a = 10;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        // finally{
        //     lock.unlockRead(stamp);
        //     System.out.println("read lock release by: " + Thread.currentThread().getName());
        // }
    }

    public void OptimisticConsumer(StampedLock lock){
        long stamp = lock.writeLock();
        System.out.println("optimistic write lock acquired by: " + Thread.currentThread().getName());
        try {
                System.out.println("performing work");
                a = 9;
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            lock.unlockWrite(stamp);
            System.out.println("write lock acquired by: " + Thread.currentThread().getName());
        }
    }

    
}
