package com.concurrency.www.test;

import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        int queueSize = 20;
        //这里可以回忆一下JVM中多线程共享内存的知识
        PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue, queueSize);

        new Thread(consumer).start();
        new Thread(producer).start();
    }

}
