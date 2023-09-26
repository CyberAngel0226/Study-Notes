package juc.t2_synchornized;

/**
 * 下述代码会出现什么问题？如何解决？
 * volatile和synchronized需要并行存在吗？
 */

public class T2_eg implements Runnable {
    private int count = 100;

    // synchronized既保证了原子性又保证了可见性
    @Override
    public /*synchronized*/ void run() {
        count --;
        System.out.println(Thread.currentThread().getName() + "count:" + count);
    }

    public static void main(String[] args) {
        T2_eg t = new T2_eg();
        for(int i=0; i<100; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }
}
