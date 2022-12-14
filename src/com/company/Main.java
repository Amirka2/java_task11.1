package com.company;

import java.time.Duration;
import java.time.Instant;

public class Main {

    static int x = 0;
    static int count = 0;

    static class LuckyThread extends Thread {
        String name;

        public LuckyThread(String name) {
            this.name = name;
        }

        private static final Object lock = new Object();

        @Override
        public void run() {
            int temp;
            while (true) {
                synchronized (lock) {
                    if (x > 999999)
                        break;
                    x++;
                    temp = x;
                }
                if ((temp % 10) + (temp / 10) % 10 + (temp / 100) % 10 == (temp / 1000)
                        % 10 + (temp / 10000) % 10 + (temp / 100000) % 10) {
                    System.out.println(temp);
                    synchronized (lock) {
                        count++;
                    }
                }
            }
        }

//        @Override
//        public void run() {
//            calc();
//        }
//        private synchronized void calc() {
//            while (x < 999999) {
//                x++;
//                if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
//                        % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
//                    System.out.print(x);
//                    System.out.println(name);
//                    count++;
//                }
//            }
//        }
    }


    public static void main(String[] args) throws InterruptedException {

        //Instant starts = Instant.now();

        Thread t1 = new LuckyThread("t1");
        Thread t2 = new LuckyThread("t2");
        Thread t3 = new LuckyThread("t3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
        //Instant ends = Instant.now();
        //System.out.println(Duration.between(starts, ends).toMillis());
    }

}
