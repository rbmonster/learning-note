package com.learning.io.nio;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: ClientDemo
 * @Author: sanwu
 * @Date: 2020/10/25 13:07
 */
public class ClientDemo {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 1111);
        BufferedWriter out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        while(true) {
//            System.out.println("socket start to read~");
//            inputStream.read(bytes);
//            System.out.println(new String(bytes));
//            TimeUnit.SECONDS.sleep(1);
            System.out.println("socket start to send~");
            out.write("socket gogo");
            out.flush();
            TimeUnit.SECONDS.sleep(10);
        }


    }
}
