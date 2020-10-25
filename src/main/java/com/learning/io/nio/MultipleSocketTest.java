package com.learning.io.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Test
 * @Author: sanwu
 * @Date: 2020/10/25 18:21
 */
public class MultipleSocketTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Socket> socketList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Socket socket = new Socket("localhost", 1111);
            socketList.add(socket);
        }
        while (true) {
            TimeUnit.SECONDS.sleep(2);
            for (Socket socket: socketList) {
                OutputStream out = socket.getOutputStream();
                String msg = socket.toString()+" : send message";
                out.write(msg.getBytes());
            }
        }
    }
}
