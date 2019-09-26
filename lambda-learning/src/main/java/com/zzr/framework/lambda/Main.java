package com.zzr.framework.lambda;

/**
 * Created by zhaozhirong on 2019/9/24.
 */
public class Main {
    public static void main(String[] args) {
        Base base = new Base(){
            @Override
            public void display() {
                System.out.println("sub");
            }
        };
        base.display();
    }
}
