package com.zzr.framework.algorithm;

/**
 * Created by zhaozhirong on 2019/6/6.
 */
public class KMP {

    static final String str = "BBC ABCDAB ABCDABCDABDE";
    static final String search = "ABCDABD";

    public static void main(String[] args) {
        System.out.println(violentMatch(str,search));
    }

    /**
     * 有一个文本串S，和一个模式串P，现在要查找P在S中的位置，怎么查找呢？
     * @param str
     * @param search
     * @return
     */

    /**
     * 暴力匹配算法
     * @param str
     * @param search
     * @return
     */
    static int violentMatch(String str,String search){
        int strLength = str.length();
        int searchLength = search.length();
        char[] strChars = str.toCharArray();
        char[] searchChars = search.toCharArray();
        int i =0,j = 0;
        while (i< strLength && j < searchLength){
            if(strChars[i] == searchChars[j]){
                i++;
                j++;
            }else {
                i = i - j +1;
                j = 0;
            }
        }
        if(j <= searchLength){
            return i-j;
        }
        return -1;
    }

}
