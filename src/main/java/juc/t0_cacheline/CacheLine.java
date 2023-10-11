package juc.t0_cacheline;

/**
 * 三级缓存
 * 缓存行，由于缓存一致性协议的存在，缓存行需要对其一般64个字节，可以写一个例子测试缓存行
 * 待优化
 */
public class CacheLine {

    long LOOP_COUNTS  = 1_0000_0000L;

    static class Node{
        volatile long t1, t2, t3, t4, t5, t6,t7;
        volatile long x;
        volatile long m1, m2, m3, m4, m5, m6, m7;
    }

    void run (Node node) {
        for (int i = 0; i < LOOP_COUNTS; i++) {
            node.x = 0;
        }
    }

    public static void main(String[] args) {
        CacheLine c = new CacheLine();
        long startTime = System.currentTimeMillis();
        Node node = new Node();

        Thread t1 = new Thread(() -> c.run(node));
        Thread t2 = new Thread(() -> c.run(node));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.println("线程运行时间：" + runningTime + "毫秒");
    }

}
