package com.zzr.framework.groovy;

import com.alibaba.fastjson.JSON;
import groovy.json.JsonOutput;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaozhirong on 2019/6/5.
 *
 *  -Xmx200m  -Xms100m  -verbose:class  -verbose:gc -XX:+PrintGCDetails   -XX:+PrintGCTimeStamps
 *  -Xmx200m  -Xms100m -verbose:gc -XX:+PrintGCDetails   -XX:+PrintGCTimeStamps
 */
public class Main {
    private static int clientNum = 10;
    private static int threadsNum = 10;
    /**
     * 计数器
     */
    final static CountDownLatch doneSignal = new CountDownLatch(clientNum);

    public static void main(String[] args) throws Exception {

        Map<Integer,GroovyObject> groovyObjectMap = new HashMap<>();
        Map<Integer,Class> groovyClassMap = new HashMap<>();
        String paramsData = getParamsData();
        String jsonData = getJsonData();
        GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
        File sourceFile = new File("E:\\myProject\\framework-learning\\groovy-learning\\src\\main\\resources\\FilterRecItem.groovy");
        GroovyObject groovyObject = null;
        Class groovyClass = null;
        if(!groovyClassMap.containsKey(123)){
            groovyClassMap.put(123,  classLoader.parseClass(new GroovyCodeSource(sourceFile)));
            groovyObjectMap.put(123, (GroovyObject) classLoader.parseClass(new GroovyCodeSource(sourceFile)).newInstance());
        }
        groovyClass = groovyClassMap.get(123);
        groovyObject = groovyObjectMap.get(123);
//            define(jsonData,paramsData,groovyClass);
//            parse(jsonData,paramsData,groovyClass);

//        ExecutorService executorService = Executors.newFixedThreadPool(threadsNum);
//        for (int i = 0; i < clientNum; i++) {
//            MyClient myClient = new MyClient(groovyObject,i+"");
//            executorService.execute(myClient);
//            if(i == 9){
//                Thread.sleep(10000);
//            }
//            doneSignal.countDown();
//        }
        System.out.println("===============");
        while (true){
            parse(jsonData,paramsData,groovyObject);
        }

    }

    static class MyClient implements Runnable{

        private GroovyObject groovyObject;
        private String filter;
        private int count;

        public MyClient(GroovyObject groovyObject, String filter) {
            this.groovyObject = groovyObject;
            this.filter = filter;
        }

        @Override
        public void run() {
            try {
                System.out.println(filter+"=======wait start======");
                doneSignal.await();
                System.out.println(filter+"=======wait end====");
                for (int i = 0; i < 1000; i++) {

                    String result = parseReturn(getJsonData(),filter,groovyObject);
                    if(!result.contains(filter)){
                        count++;
                    }
                }
                System.out.println(filter+"========count=="+count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void define(String jsonData,String paramsData,Class groovyClass) throws Exception {
        GroovyObject instance = (GroovyObject)groovyClass.newInstance();
        String result = (String) instance.invokeMethod("filter",new Object[]{jsonData,paramsData});
//        System.out.println(result);
    }

    public static void parse(String jsonData,String paramsData,Class groovyClass) throws Exception {
        GroovyObject instance = (GroovyObject)groovyClass.newInstance();//proxy
        String result = (String)instance.invokeMethod("filter",new Object[]{jsonData,paramsData});
        System.out.println(result);
    }

    public static void parse(String jsonData, String paramsData,GroovyObject instance) throws Exception {
        String result = (String)instance.invokeMethod("filter",new Object[]{jsonData,paramsData});
    }

    public static String parseReturn(String jsonData, String paramsData,GroovyObject instance) throws Exception {
        return  (String)instance.invokeMethod("filter",new Object[]{jsonData,paramsData});
    }

    public static String getJsonData(){
        List<RecItem> recItemList = new ArrayList<>();
        for (int i = 0; i < clientNum; i++) {
            RecItem recItem = new RecItem(i+"");
            recItemList.add(recItem);
        }
        return JSON.toJSONString(recItemList);
    }

    public static String getParamsData(){
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("userPin","zzr");
        return JSON.toJSONString(stringStringHashMap);
    }
}
