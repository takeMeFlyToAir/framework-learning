package com.zzr.framework.common.util.hash;

/**
 * Created by zhaozhirong on 2019/8/23.
 */
public class HashTest {
    public static void main(String[] args) {
        String key1 = "abc";
        String key2 = "abd";
        String key3 = "bcd";

        MurmurHash hash = new MurmurHash();


        System.out.println(-10%3);
    }

    public static int hashCode(char[] value) {
        int h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                System.out.print(h+"====="+31*h+"======"+val[i]);
                h = 31 * h + val[i];
                System.out.println("======"+h);
            }
        }
        return h;
    }
}
