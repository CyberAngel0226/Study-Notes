package juc.t3_volatile.non_atomic;

/**
 * volatile 无法保证原子性，一个线程中执行 count++并不是原子性的操作
 * 下面是一个测试例子，需要对m加 synchronized 才能保证原子性
 */

public class Count {
    volatile int count = 0;

    void  m() {

    }

    public static void main(String[] args) {

    }
}
