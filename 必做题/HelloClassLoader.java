package com.learn;

import java.io.DataInputStream;
import java.io.InputStream;

import java.lang.reflect.Method;
public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Class<?> testClass=new HelloClassLoader().findClass("Hello");
            Object object=testClass.newInstance();
            Method method=testClass.getMethod("hello");
            method.invoke(object);
        }catch (Exception e){

        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            InputStream in = getResourceAsStream(name+".xlass");
            DataInputStream dt = new DataInputStream(in);
            int length=dt.available();
            byte[] bytes = new byte[length];
            int count=0;
            while (dt.available()>0) {
                byte newBype= (byte) (255-dt.readByte());
                bytes[count] = newBype;
                count++;
            }
            return defineClass(name,bytes,0,bytes.length);
        }catch (Exception e){

        }
        return null;
    }
}
