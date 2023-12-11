package com.example.javapractice.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolTest {
    
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 
            4, 
            10, 
            TimeUnit.MINUTES, 
            new ArrayBlockingQueue<>(2), 
            new CustomThreadFactory(), 
            new ThreadPoolExecutor.DiscardOldestPolicy());

        IntStream.range(0, 9).forEach(
            (index) -> executor.submit(() -> {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                System.out.printf("Task %d processed by : %s \n", index, Thread.currentThread().getName());
        }));

        executor.shutdown();
    }    
}

class CustomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r){
        Thread th =  new Thread(r);
        th.setPriority(Thread.NORM_PRIORITY);
        th.setDaemon(false);
        return th;
    }

}

class CustomRejectHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor){
        System.out.println("Task rejected: " + r.toString());
    }
}
