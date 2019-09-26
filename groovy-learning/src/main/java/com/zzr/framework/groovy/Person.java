package com.zzr.framework.groovy;

public class Person {
    public String name;
    public String address;
    public Integer age;
    
    public Person(String name, String addr, Integer age){
        this.name = name;
        this.address = addr;
        this.age = age;
    }
    
    public String toString(){
        return String.format("[Person: name:%s, address:%s, age:%s]", name,address, age);
    }
}