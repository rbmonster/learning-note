package com.learning.io.bio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * 多线程服务器实现
 * </pre>
 *
 * @version v1.0
 * @ClassName: SocketServerConcurrent
 * @Author: sanwu
 * @Date: 2020/10/25 10:39
 */
public class SocketServerConcurrent {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(3333);
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            Socket accept = serverSocket.accept();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            InputStream inputStream = accept.getInputStream();
            executorService.execute(() -> {
                byte[] buffer = new byte[1024];
                try {
                    System.out.println("server start to send~");
                    out.write("buffer sever write"); out.flush();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("server start to read~");
                    inputStream.read(buffer);
                    System.out.println(new String(buffer));
                    TimeUnit.SECONDS.sleep(1);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

    }
}
