package com.example.javapractice;

public class SharedResource {
    
    boolean isItemPresent = false;

    public synchronized void addItem(){
        isItemPresent = true;
        System.out.println("Producer thread calling the notify()");
        notifyAll();
    }

    public synchronized void consumeItem(){
        System.out.println("Consumer Thread inside comsumeItem method");
        if(!isItemPresent){
            try {
                System.out.println("Consumer Thread waiting");
                wait(); //release all the monitor lock
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        System.out.println("consumed item");
        isItemPresent = false;
    }
}
