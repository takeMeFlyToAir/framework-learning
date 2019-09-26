package com.zzr.framework.templateMethod;

/**
 * Created by zhaozhirong on 2019/6/11.
 *
 * 抽象模板中的基本方法尽量设计为protected类型，符合迪米特法则，不需要暴露
 的属性或方法尽量不要设置为protected类型。实现类若非必要，尽量不要扩大父类中的访问
 权限。
 */
public class Client {
    public static void main(String[] args) {
        AbstractClass concreteClassA = new ConcreteClassA();
        AbstractClass concreteClassB = new ConcreteClassB();
        concreteClassA.templateMethod();
        concreteClassB.templateMethod();
    }
}
