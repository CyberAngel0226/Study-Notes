package juc.util;

import java.util.concurrent.locks.LockSupport;

/**
 * 多线程交替打印 1~26和a~z
 */

public class Question {

    Thread t1 = null, t2 = null;
    volatile int count = 1;

    public void test1() {

        t1 = new Thread(() -> {
            while (count <= 26) {
                System.out.println(count);
                count++;
                LockSupport.unpark(t2);
                LockSupport.park();

            }
        });

        t2 = new Thread(() -> {
            while (count <= 26) {
                LockSupport.park();
                System.out.println((char) ('a' + count - 2));
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        Question q = new Question();
        q.test1();
    }
}
