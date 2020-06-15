package com.learning.io;

import java.io.*;

/**
 * <pre>
 * @Description: 格式化内存输入
 * </pre>
 *
 * @version v1.0
 * @ClassName: FormattedMemoryInput
 * @Author: 86159
 * @Date: 2020/3/22 20:00
 */
public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {
        // 用字符组的方法批量读取数据
        DataInputStream input = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("").getBytes()));
        while (true) {
            System.out.println(input.readByte());
        }
    }
}
