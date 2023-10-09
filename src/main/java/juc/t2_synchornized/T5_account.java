package juc.t2_synchornized;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟银行，对写方法加锁，读方法不加锁
 * 并且如何避免脏读，ConcurrentHashMap可以保证线程安全
 */
public class T5_account {

    Map<String, Double> account;

    T5_account() {
        // this.account = new HashMap<>();
        this.account = new ConcurrentHashMap<>();
    }

    public double getAccount(String name) {
        if(this.account.containsKey(name)) {
            return this.account.get(name);
        } else {
            return -1;
        }
    }

    synchronized void setAccount(String name, double account) {
        this.account.put(name, this.account.getOrDefault(name, 0.0) + account);
    }

    public static void main(String[] args) {
        T5_account t = new T5_account();
        Thread t1 = new Thread(() -> {
            t.setAccount("liuxingzhi", 200.4);
        });
        Thread t2 = new Thread(() -> {
            System.out.println(t.getAccount("liuxingzhi"));
        });
        t1.start();
        t2.start();
    }
}                         