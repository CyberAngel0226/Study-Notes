package juc.t3_volatile.singleton;

/**
 * 饿汉式单例
 * 类加载到内存后，仅实例化一个单例，JVM保证线程安全
 * 唯一缺点：不论是否用到，类装载时候都会加载
 */

public class HungrySingleton {
    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton() {};

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }
}
