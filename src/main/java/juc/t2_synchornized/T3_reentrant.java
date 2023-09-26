package juc.t2_synchornized;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁：一个同步方法可以调用另一个同步方法
 * 一个线程已经拥有了某个对象的锁，再次申请时仍然会的到该对象的锁
 * 也就是说synchronized获得的锁是可重入锁
 * 可以写一个子类重写父类方法加锁的例子，super.m();
 */
public class T3_reentrant {
    synchronized void run1() {
        System.out.println("run1:start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        run2();
        System.out.println("run1:end.");
    }

    synchronized void run2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("run2:start...");
    }

    public static void main(String[] args) {
        // new T3_Reentrant().run1();
        new Child().run();

    }

    static class Father {
        synchronized void run() {
            System.out.println("run:start...");
        }
    }

    static class Child extends Father {
        @Override
        synchronized void run(){
            System.out.println("child run...");
            super.run();
        }
    }

}
