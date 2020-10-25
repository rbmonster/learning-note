package com.learning.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * <pre>
 * @Description: 新的IO 使用channel 管道与 ByteBuff进行通信
 * </pre>
 *
 * @version v1.0
 * @ClassName: BufferToText
 * @Author: 86159
 * @Date: 2020/3/22 21:56
 */
public class ChannelBufferToText {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("test.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();
        fc = new FileInputStream("test.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        System.out.println("第一次输出：");
        System.out.println(buff.asCharBuffer()); // 输出为空无法直接转成char 输出

        // 以下两种是编码后输出的方式
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("第二次输出：");
        System.out.println("Decoded using " +encoding + ":" +
                Charset.forName(encoding).decode(buff));

        fc = new FileOutputStream("test.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();

        // 为读取相关
        fc = new FileInputStream("test.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println("第三次输出：");
        System.out.println(buff.asCharBuffer());


        fc = new FileInputStream("test.txt").getChannel();
        buff = ByteBuffer.allocate(1024);
        buff.asCharBuffer().put("Some text");
        fc.write(buff);
        fc.close();


        fc = new FileInputStream("test.txt").getChannel();
        buff.clear();
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
    }
}
