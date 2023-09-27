package juc.t3_volatile.singleton;

/**
 * 双重检查锁懒汉式单例，判断是否需要加 volatile
 */

public class DoubleCheckLock {
    private static volatile DoubleCheckLock INSTANCE;

    private DoubleCheckLock(){}

    // 不需要加锁到方法上
    public static DoubleCheckLock getInstance() {
        // todo 部分业务逻辑代码
        if(INSTANCE == null) { // 外部判断不抢占锁，可以节约很多线程资源
            // 双重检查锁
            synchronized (DoubleCheckLock.class) {
                if(INSTANCE == null) { // 内部抢占完锁在进行判断
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*
                     *  实例对象时会执行三步 申请内存，成员变量初始化，变量赋值给INSTANCE
                     *  不加volatile 在很高并发的情况下会发生指令重排序，变量初始化之前可能会发生变量赋值，就是对象在初始化未完成时赋值给INSTANCE
                     *  此时INSTANCE不为null了，线程而进入判空会发生错误，拿到一个错误的变量
                     */
                    INSTANCE = new DoubleCheckLock();

                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton.getInstance().hashCode());
            }).start();
        }
    }

}
