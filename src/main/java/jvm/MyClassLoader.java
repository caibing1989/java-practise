package jvm;

import java.io.*;

/**
 * @Description: 自定义类加载器
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class MyClassLoader extends ClassLoader {
    private String myName = "";

    public MyClassLoader(String myName) {
        this.myName = myName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);

        return this.defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {
        byte[] data = null;
        InputStream input = null;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        className = className.replace(".", "/");

        try {
            input = new FileInputStream(new File("classes/" + className + ".class"));

            int a = 0;
            while ((a = input.read()) != -1) {
                out.write(a);
            }

            data = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }
}
