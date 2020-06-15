package com.learning.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * <pre>
 * @Description: 直接从内存读取输入
 * </pre>
 *
 * @version v1.0
 * @ClassName: MemoryInput
 * @Author: 86159
 * @Date: 2020/3/22 19:43
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(path);
        StringReader in = new StringReader(BufferedInputFile.read(path +"com/learning/io/MemoryInput.class"));
        int c;
        while ((c = in.read()) != -1) {
            System.out.println((char)c);
        }
    }
}
