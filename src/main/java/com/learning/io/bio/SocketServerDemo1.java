package com.learning.io.bio;

import com.sun.xml.internal.stream.util.BufferAllocator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo2
 * @Author: sanwu
 * @Date: 2020/10/25 10:02
 */
public class SocketServerDemo1 {
    public static void main(String[] args)  throws Exception{
        ServerSocket serverSocket = new ServerSocket(2222);
        byte[] buffer = new byte[1024];
        Socket accept = serverSocket.accept();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        InputStream inputStream = accept.getInputStream();
        while(true) {
            System.out.println("server start to send~");
            out.write("buffer sever write");
            out.flush();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("server start to read~");
            inputStream.read(buffer);
            System.out.println(new String(buffer));
            TimeUnit.SECONDS.sleep(1);

        }
    }
}
