package com.learning.io.bio;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo1
 * @Author: sanwu
 * @Date: 2020/10/25 9:59
 */
public class SocketDemo1 {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 2222);
        BufferedWriter out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        while(true) {
            System.out.println("socket start to read~");
            inputStream.read(bytes);
            System.out.println(new String(bytes));
            TimeUnit.SECONDS.sleep(1);
            System.out.println("socket start to send~");
            out.write("socket gogo");
            out.flush();
            TimeUnit.SECONDS.sleep(1);
        }


    }
}
