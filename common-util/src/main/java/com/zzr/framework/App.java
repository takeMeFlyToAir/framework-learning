package com.zzr.framework;

import com.zzr.framework.common.util.provider.ZzrException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        try {
            dd();
        } catch (ZzrException e) {
            System.out.println("exception");
            e.printStackTrace();
        }
    }

    public static void dd()throws ZzrException{
        System.out.println(1);
        throw new ZzrException("zzz");
    }
}
