package com.example.javapractice;

public class ConsumeProducerMain {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(() ->{
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            sharedResource.addItem();
        });

        Thread consumer = new Thread(() -> {
            sharedResource.consumeItem();
        });

        producer.start();
        try {
            System.out.println("waiting producer to add element");
            producer.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        consumer.start();
        // producer.start();
    }
    
}
