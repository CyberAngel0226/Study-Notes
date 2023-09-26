package juc.t1_thread;

public class T2_Sleep_Yield_Join {

    // Sleep 懂得都懂，暂停线程
    static void testSleep(){
        new Thread(()->{
            for(int i=0; i<10; i++) {
                System.out.println("Sleep");
                try{
                    Thread.sleep(5000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 线程返回就绪状态，进入等待队列，和下一个线程一起争夺资源
    static void testYield() {
        new Thread(() ->{
            for(int i=0; i<100; i++) {
                System.out.println("yield" + i);
                if(i%10 == 0) {
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() ->{
            for(int i=0; i<100; i++) {
                System.out.println("--------" + i);
                if(i%10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    // 将join线程加入当前线程执行，待第二个线程执行完再重新执行第一个。
    static void testJoin() {
        Thread t1 = new Thread(() ->{
            for(int i=0; i<10; i++) {
                System.out.println("T1" + i);
            }
        });

        Thread t2 = new Thread(() ->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(int i=0; i<10; i++) {
                System.out.println("T2" + i);
            }
        });
        t2.start();
        t1.start();
    }


    public static void main(String[] args) {
        testSleep();
        testYield();
        testJoin();
    }
}
