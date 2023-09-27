package juc.t3_volatile;

import java.util.concurrent.TimeUnit;

public class T {
    volatile boolean running = true;

    void m() {
        System.out.println("m start");
        while(running) {
            // todo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T t = new T();
        // lambda
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
