package juc.t4_synchronized_optimization;

/**
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o编程另外一个对象，则锁定的对象发生改变
 * 应避免锁定的对象改变
 */
public class ObjectChange {
    // 记得加final防止锁的对象发生改变
    final Object o = new Object();

}
