package jvm.classloader;

import java.lang.reflect.Method;

public class TestMyClassLoader {
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("bytedance\\desktop\\test\\jvm.classloader");
        Class c = myClassLoader.loadClass("jvm.classloader.test");
        if(c != null) {
            Object obj = c.newInstance();
            Method method = c.getMethod("say", null);
            method.invoke(obj, null);
            System.out.println(c.getClassLoader().toString());
        }
    }
}
