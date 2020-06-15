package com.learning.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 * @Description: TODO(一句话描述该类的功能)
 * </pre>
 *
 * @version v1.0
 * @ClassName: ArraySearch
 * @Author: 86159
 * @Date: 2020/4/20 21:10
 */
public class ArraySearch {
    public static void main(String[] args) {
//        new ArraySearch().Find(8,{{1,6,8},{2,}})

        new ArraySearch().replaceSpace(new StringBuffer("     "));
    }


    public boolean Find(int target, int [][] array) {
        int x = 0, y=0;
        for(;y <array[0].length;y++) {
            if(target == array[x][y]){
                return true;
            }
            if(target < array[x][y]){
                y--;
                break;
            }
        }
        for(;x<array.length;x++){
            if(target == array[x][y]){
                return true;
            }
            if(target < array[x][y]){
                break;
            }
        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {
        String temp = str.toString();
        if(temp.equals("")){
            return "";
        }
        char[] ch = temp.toCharArray();
        int len = ch.length;
        for(int i=0;i<  ch.length;i++) {
            if(ch[i] == ' '){
                len= len + 2;
            }
        }
        char[] res = new char[len];
        int i = ch.length-1;
        int j = len-1;
        while( i >=0) {
            if(ch[i]!= ' '){
                res[j--]= ch[i--];
            }else {

                res[j--]='0';
                res[j--]='2';
                res[j--]='%';
                i--;
            }
        }
        return new String(res);
    }
}
