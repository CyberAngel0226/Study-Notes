package juc.t4_synchronized_optimization;


/**
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应避免锁定的对象改变
 */
public class ObjectChange {
    // 记得加final防止锁的对象发生改变
    /*final*/ Object lock = new Object();
    public void test() {
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " acquired lock on object");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " acquired lock on object");
            }
        });
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 锁定的对象重新实例化
       // lock = new Object();
        thread2.start();
    }

    public static void main(String[] args) {
        new ObjectChange().test();
    }
}
