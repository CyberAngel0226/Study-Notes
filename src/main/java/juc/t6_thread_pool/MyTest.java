package juc.t6_thread_pool;

import java.util.concurrent.*;

public class MyTest {
    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newFixedThreadPool(1);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread();
                        t.setName("a");
                        return t;
                    }
                }, new ThreadPoolExecutor.AbortPolicy());
        // 线程池执行任务

    }
}
