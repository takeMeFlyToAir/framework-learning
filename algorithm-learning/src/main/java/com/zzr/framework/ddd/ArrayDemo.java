package com.zzr.framework.ddd;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhaozhirong on 2019/9/18.
 */
public class ArrayDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("6", "1", "3", "1","2");

        Collections.sort(strings);//sort方法在这里

        for (String string : strings) {

            System.out.println(string);
        }



        Integer[][] old = new Integer[11][11];
        for (int i =0; i < 11; i++){
            for(int j =0; j < 11; j++){
                old[i][j] = 0;
            }
        }
        old[1][2] = 1;
        old[2][3] = 2;
        Integer count = 0;
        for (int i =0; i < 11; i++){
            for(int j =0; j < 11; j++){
                if (old[i][j] != 0){
                    count++;
                }
            }
        }
        Integer sum = count + 1;
        Integer[][] newArray = new Integer[sum][3];

        newArray[0][0] = 11;
        newArray[0][1] = 11;
        newArray[0][2] = count;
        count=1;
        for (int i =0; i < 11; i++){
            for(int j =0; j < 11; j++){
                if (old[i][j] != 0){
                    newArray[count][0] = i;
                    newArray[count][1] = j;
                    newArray[count][2] = old[i][j];
                    count++;
                }
            }
        }
        for (int i=0; i < sum; i++){
            for (int j=0; j < 3; j++){
                System.out.println(newArray[i][j]);
            }
        }
    }
}
