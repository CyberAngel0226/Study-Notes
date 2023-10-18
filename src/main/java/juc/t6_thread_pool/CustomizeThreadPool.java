package juc.t6_thread_pool;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomizeThreadPool {

    static int count = -1;

    static class Task implements Runnable {


        @Override
        public void run() {
            while(count == -1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
                );
        Task t1 = new Task();
        tpe.execute(t1);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("--------------------------");

        Task t2 = new Task();
        tpe.execute(t2);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("--------------------------");

        Task t3 = new Task();
        Task t4 = new Task();
        Task t5 = new Task();
        tpe.execute(t3);
        tpe.execute(t4);
        tpe.execute(t5);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("--------------------------");

        Task t6 = new Task();
        tpe.execute(t6);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("--------------------------");

    }
}
