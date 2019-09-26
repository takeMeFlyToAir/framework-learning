package com.zzr.spring.mybatis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhaozhirong on 2019/5/14.
 */
public class MyPropertiesUtil {

    public static Properties load(String path){
        Properties properties = new Properties();
        InputStream inStream = MyPropertiesUtil.class.getClassLoader().getResourceAsStream(path);
        try {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
