package juc.t4_synchronized_optimization;

/**
 * synchronized优化
 * 锁的细化：同步代码块越少越好
 * 锁的粗化：锁过多，可以直接锁方法
 */
public class FineCoarseLock {
    int count = 0;

    synchronized  void m1() {
        // todo 业务逻辑
        count ++;
    }

    // m2方法相较于m1锁更细化
    void m2() {
        // todo 业务逻辑
        synchronized (this) {
            count ++;
        }
    }
}
