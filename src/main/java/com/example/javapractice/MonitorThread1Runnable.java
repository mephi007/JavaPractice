package com.example.javapractice;

public class MonitorThread1Runnable implements Runnable {

    MonitorLockExample obj;

    public MonitorThread1Runnable(MonitorLockExample obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'run'");
        obj.task1();
    }
    
}
