package juc.t1_thread;

public class T1_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("This is my thread");
        }
    }
    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("This is my run");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        // lambda
        new Thread(() -> {
            System.out.println("Hello Lambda");
        }).start();
    }
}
