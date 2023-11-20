package com.example.javapractice.Synchronized;

public class SharedResource {
    boolean isAvailable = false;

    public synchronized void producer(){
        try{
            System.out.println(String.format("Lock acquired by: %s", Thread.currentThread().getName()));
            isAvailable = true;
            Thread.sleep(4000);
        }catch(Exception e){ }
        System.out.println(String.format("LOck release by: %s", Thread.currentThread().getName()));
    }
}
