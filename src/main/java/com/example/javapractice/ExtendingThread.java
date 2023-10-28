package com.example.javapractice;

public class ExtendingThread extends Thread {
    
    @Override
    public void run(){
        System.out.println("code is executing in Extending class --> " + Thread.currentThread().getName());
    }
}
