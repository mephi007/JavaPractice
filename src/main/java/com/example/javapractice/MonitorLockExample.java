package com.example.javapractice;

public class MonitorLockExample {

    public synchronized void task1(){
        try{
            System.out.println("inside task 1");
            Thread.sleep(10000);
            System.out.println("task1 completed");
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void task2(){
        System.out.println("inside task2 before synchronized block");
        synchronized(this){
            System.out.println("inside task2 after synchronized block");
        }
    }

    public void task3(){
        System.out.println("inside task3");
    }
    
}
