package jvm.object;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.Lock;

public class ObjectLock {
    public static void main(String[] args) {
        Object obj = new Object();

        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        new ObjectLock().printTest();
    }

    void printTest(){
        Node node = new Node();
        System.out.println(ClassLayout.parseInstance(node).toPrintable());
        node.flag= true;
        node.val = 2;
        System.out.println(ClassLayout.parseInstance(node).toPrintable());
    }

    // 非静态类好像比静态类多一个jvm.object.ObjectLock Node.this$0  占四个字节
     class Node {
        int val;
        boolean flag;
        String str;
    }
}
