package com.example.javapractice.ConsumerProducerProblem;


public class ProducerConsumerProblemWithQueue {
    
    public static void main(String[] args) {
        SharedQueue cpq = new SharedQueue(3);
        Thread producer = new Thread(() -> {
                try {
                    cpq.addToQueuue();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });

        Thread consumer = new Thread(
            () -> {
                try {
                    cpq.removeFromQueue();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        );
        consumer.start();
        producer.start();
    }
}
