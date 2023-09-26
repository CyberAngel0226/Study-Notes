package juc.t2_synchornized;

/**
 * 注意 Synchronized是对某个对象加锁，线程必须获取锁才能针对对象或者对应代码块进行执行
 */

public class  T1_concept{
    private int count = 10;
    private Object o = new Object();


    //synchronized (this) 等值 public synchronized void m()
    public void m(){
        synchronized (o) {
            count --;
            System.out.println(Thread.currentThread().getName() + "count:" + count);
        }
    }

    // 等值与Synchronized(Concept.class) 静态锁定等同于锁定整个对象
    public synchronized static void n() {
    }
}
