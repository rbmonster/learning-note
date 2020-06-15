package com.learning.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <pre>
 * @Description: 典型的缓存输入文件案例
 * </pre>
 *
 * @version v1.0
 * @ClassName: BufferedInputFile
 * @Author: 86159
 * @Date: 2020/3/22 19:37
 */
public class BufferedInputFile {
    public static void main(String[] args) throws IOException {
        read("test.txt");
    }
    public static String read(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder =new StringBuilder();
        String s;
        while((s=in.readLine())!= null) {
            stringBuilder.append(s);
        }
        in.close();
        return stringBuilder.toString();
    }
}
