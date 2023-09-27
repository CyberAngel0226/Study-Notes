package juc.t3_volatile.singleton;

/**
 * 懒汉式，虽然达到了按需初始化的目的，但是却有线程不安全的问题
 * 可以通过synchronized解决，但是也会使性能下降
 */

public class LazySingleton {
    private static LazySingleton INSTANCE;

    private LazySingleton(){}

    public static synchronized LazySingleton getInstance() {
        if(INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new LazySingleton();
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
