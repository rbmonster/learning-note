package com.learning.io;

import java.io.*;

/**
 * <pre>
 * @Description: 数据的存储和恢复
 *           将数据临时存储到文件中
 *           在从文件中读取
 *          设置写入时为精确读取和写入
 * </pre>
 *
 * @version v1.0
 * @ClassName: StoringAndRecoveryingData
 * @Author: 86159
 * @Date: 2020/3/22 20:38
 */
public class StoringAndRecoveringData {

    public static void main(String[] args) throws IOException {
        String fileName= "test.txt";
        DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        output.writeDouble(12312.123);
        // 使用UTF-8 写入数据
        output.writeUTF("sdfsadf哈哈哈哈");
        output.writeUTF("it is end!");
        output.close();

        DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        // 精确读取 必须每行都匹配数据
        System.out.println(input.readDouble());
        System.out.println(input.readUTF());
        System.out.println(input.readUTF());
    }
}
