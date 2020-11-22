package com.learning.io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * 使用socket 进行nio
 * </pre>
 *
 * @version v1.0
 * @ClassName: Test
 * @Author: sanwu
 * @Date: 2020/10/25 18:21
 */
public class ClientMultipleSocketTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Socket> socketList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Socket socket = new Socket("localhost", 1111);
            socketList.add(socket);
        }
        while (true) {
            TimeUnit.SECONDS.sleep(2);
            for (Socket socket: socketList) {
                OutputStream out = socket.getOutputStream();
                String msg = socket.toString()+" : send message " +random.nextInt(123123);
                out.write(msg.getBytes());
                TimeUnit.SECONDS.sleep(1);

                InputStream inputStream = socket.getInputStream();
                byte []bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                System.out.println(new String(bytes));
            }
        }
    }
}
