package com.zzr.framework.groovy;

import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;


/**
 * Created by zhaozhirong on 2019/6/4.
 */
public class ScriptEngineDemo {


    public static void main(String[] args) throws Exception {
        noParam();
        hasParam();
    }

    public static void noParam() throws Exception {

        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine("E:\\myProject\\framework-learning\\groovy-learning\\src\\main\\resources\\");
        Class scriptClass = groovyScriptEngine.loadScriptByName("filterForScriptEngine.groovy");
        GroovyObject groovyObject = (GroovyObject) scriptClass.newInstance();
        Object filter = groovyObject.invokeMethod("filter", null);
        System.out.println(filter);
    }

    public static void hasParam() throws Exception {
        Person person = new Person("wchi", "nanjing", 30);
        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine("E:\\myProject\\framework-learning\\groovy-learning\\src\\main\\resources\\");
        Class scriptClass = groovyScriptEngine.loadScriptByName("filterForScriptEngine.groovy");
        GroovyObject groovyObject = (GroovyObject) scriptClass.newInstance();
        Object filter = groovyObject.invokeMethod("filter", new Object[]{person,"lxi"});
        System.out.println(filter);
    }
}
