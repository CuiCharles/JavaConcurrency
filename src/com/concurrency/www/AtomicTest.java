package com.concurrency.www;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicTest {


    private static void test1(){

        final int loopCount = 1000;
        int threadCount = 10;


        final NonSafeSeq seq1 = new NonSafeSeq();
        final SafeSeq seq2 = new SafeSeq();
        final CountDownLatch l = new CountDownLatch(threadCount);

        for(int i = 0 ; i < threadCount; i ++){
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    for(int j = 0; j < loopCount; j++){

                        seq1.inc();
                        seq2.inc();
                    }

                    System.out.println("finished : " + index);
                    l.countDown();
                }
            }).start();
        }



        try {
            l.await();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println("both have finished....");

        System.out.println("NonSafeSeq:" + seq1.get());
        System.out.println("SafeSeq with atomic: " + seq2.get());


    }


    public static void main(String[] args){
        test1();
    }



}



class NonSafeSeq{

    private long count = 0;

    public void inc(){
        count++;
    }


    public long get(){
        return count;
    }

}


class SafeSeq{
    private AtomicLong count = new AtomicLong(0);

    public void inc(){
        count.incrementAndGet();
    }

    public long get(){
        return count.longValue();
    }


}
