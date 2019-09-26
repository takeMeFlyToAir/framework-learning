package com.zzr.framework.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

/**
 * Created by zhaozhirong on 2019/6/4.
 */
public class GroovyClassLoaderDemo {


    public static void main(String[] args) throws Exception {
        parse();
        load();
    }
    /**
     * from source file of *.groovy
     */
    public static void parse() throws Exception{
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        File sourceFile = new File("E:\\myProject\\framework-learning\\groovy-learning\\src\\main\\resources\\filter.groovy");
        Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
        GroovyObject instance = (GroovyObject)testGroovyClass.newInstance();//proxy
        String result = (String)instance.invokeMethod("filter",null);
        System.out.println(result);
        //here
        instance = null;
        testGroovyClass = null;
    }


    public static void load() throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\myProject\\framework-learning\\groovy-learning\\src\\main\\resources\\FilterRecItem.class"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for(;;){
            int i = bis.read();
            if( i == -1){
                break;
            }
            bos.write(i);
        }
        Class testGroovyClass = classLoader.defineClass(null, bos.toByteArray());
        //instance of proxy-class
        //if interface API is in the classpath,you can do such as:
        //MyObject instance = (MyObject)testGroovyClass.newInstance()
        GroovyObject instance = (GroovyObject)testGroovyClass.newInstance();
        String result = (String)instance.invokeMethod("filter",null);
        System.out.println(result);
        //here
        instance = null;
        testGroovyClass = null;
    }
}
