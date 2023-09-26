package juc.t2_synchornized;

/**
 *程序中如果出现异常，默认情况锁会被释放，会有程序乱入，可以参考下自己写个例子。
 *比如一个线程出现异常，其释放锁可能会导致其他线程乱入，导致数据异常。
 */
public class T4_abnormal implements Runnable{

    int count = 0;

    @Override
    public synchronized void run() {
        while(true) {
            count ++;
            System.out.println(Thread.currentThread().getName() + "count:" + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(count == 5) {
                // Division by zero 出现异常后锁会被Thread2抢占
                int i = 1/0;
            }
        }
    }

    public static void main(String[] args) {
        T4_abnormal t = new T4_abnormal();
        Thread t1 = new Thread(t, "Thread1");
        Thread t2 = new Thread(t, "Thread2");
        t1.start();
        t2.start();
    }
}
