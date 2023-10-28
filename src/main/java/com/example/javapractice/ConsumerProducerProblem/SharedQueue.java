package com.example.javapractice.ConsumerProducerProblem;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    Queue<Integer> queue;
    int size;

    public SharedQueue(int capacity){
        this.queue = new LinkedList<>();
        this.size = capacity;
    }

    public void addToQueuue() throws InterruptedException{
        int value = 0;
        while(true){
            synchronized(this){
                if(queue.size() == size){
                    System.out.println("queue is full, waiting on consumer to consume");
                    wait();
                }
                System.out.println("adding --> " + value);
                queue.add(value++);
                notifyAll();
                Thread.sleep(100);
            }
        }
    }

    public void removeFromQueue() throws InterruptedException{
        // int value = -1;
        while(true){
            synchronized(this){
                if(queue.isEmpty()){
                    System.out.println("queue is empty, waiting on producer to produce");
                    wait();
                }

                int value = queue.poll();
                System.out.println("removed --> " + value);
                notifyAll();
                Thread.sleep(500);
            }
        }
    }
}
