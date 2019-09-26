package com.zzr.framework.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.springframework.scripting.groovy.GroovyScriptFactory;

import java.util.Date;

/**
 * Created by zhaozhirong on 2019/6/4.
 * GroovyShell
 * 执行简单脚本
 *
 */
public class GroovyShellDemo {

    public static void main(String[] args) {
        evalParam();
        getReturnVal();
        executeMethod();
    }

    /**
     * 传参
     */
    public static void evalParam(){
        Binding binding = new Binding();
        GroovyShell groovyShell = new GroovyShell(binding);
        binding.setVariable("name","zzr");
        groovyShell.evaluate("println('hyy is '+name)");
        groovyShell.evaluate("println 'Hello World! I am ' + name;");
    }

    /**
     * 获取返回值
     */
    public static String getReturnVal(){
        Binding binding = new Binding();
        GroovyShell groovyShell = new GroovyShell(binding);
        groovyShell.evaluate("date = new Date();");
        Date date = (Date)binding.getVariable("date");
        System.out.println("Date:" + date.getTime());
        return date.toString();
    }

    public static void executeMethod(){
        Binding binding = new Binding();
        GroovyShell groovyShell = new GroovyShell(binding);
        binding.setVariable("list", new String[]{"A","B","C"});
        String str = (String) groovyShell.evaluate("def call(){return list.join('-')};call();");
        System.out.println(str);

    }
}
