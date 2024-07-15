package jvm.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    public String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 拼接类的全限定名称
        String path = classPath + File.separatorChar + name.replace('.', File.separatorChar) +".class";
        byte []bytes;
        try {
            // 加载类的字节码数据
            InputStream inputStream = new FileInputStream(path);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte []buffer = new byte[2048];
            int len = 0;
            while((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            bytes = outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(bytes != null){
            return defineClass(name, bytes, 0, bytes.length);
        }
        return super.findClass(name);
    }
}
