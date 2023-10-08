package juc.t5_cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决问题时可以采用更高效的方法，比如Java的AtomXX类
 * AtomXX类本身方法均为原子性的，但是并不能保证多个方法连续调用也为原子性
 * 通过CAS保证线程安全
 */

public class T1_AtomicInteger {
    // volatile int count = 0;
     AtomicInteger count = new AtomicInteger(0);

    void m() {
        for(int i=0; i<10000; i++) {
            // 自增，相当于线程安全的count++
            count.incrementAndGet();

        }
    }

    public static void main(String[] args) {
        T1_AtomicInteger t = new T1_AtomicInteger();

        List<Thread> threads = new ArrayList<>();

        for(int i=0; i<10; i++) {
            threads.add(new Thread(t::m,"thread" + i));
        }

        threads.forEach(Thread::start);

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(t.count);
    }
}
