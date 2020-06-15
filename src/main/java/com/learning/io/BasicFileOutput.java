package com.learning.io;

import java.io.*;

/**
 * <pre>
 * @Description: 文本输入输出的快捷方式
 * </pre>
 *
 * @version v1.0
 * @ClassName: BasciFileOutput
 * @Author: 86159
 * @Date: 2020/3/22 20:09
 */
public class BasicFileOutput {
    public static void main(String[] args) throws IOException {
        String fileName = "test.txt";
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        int lineCount = 1;
        StringBuilder sb = new StringBuilder();
        String s= "";
        while((s=input.readLine())!= null) {
            sb.append(s);
            System.out.println(lineCount++ + ":" + s);
        }

        //以下两种写法一致， 第二种写法为java 5后做的简单优化
//        PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        PrintWriter output = new PrintWriter(fileName);
        output.println(sb);
        output.close();
        System.out.println(BufferedInputFile.read(fileName));

        System.out.println("ordinary read");
        byte[] by = new byte[1024];
        FileInputStream fileInput = new FileInputStream(new File(fileName));
        while (fileInput.read(by)!= -1) {
            System.out.println(new String(by,0,by.length));
        }

    }
}
