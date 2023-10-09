package juc.t3_volatile.non_atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 无法保证原子性，一个线程中执行 count++并不是原子性的操作
 * 下面是一个测试例子，需要对m加 synchronized 才能保证原子性
 */

public class Count {
     volatile int count = 0;
   // AtomicInteger count = new AtomicInteger(0);

    void m() {
        for(int i=0; i<10000; i++) {
            count ++;
        }

    }

    public static void main(String[] args) {
        Count c = new Count();
        for(int i=0; i<10; i++) {
            new Thread(c::m).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(c.count);
    }
}
