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

        // producer.start();
        consumer.start();
        producer.start();
    }
    
}
