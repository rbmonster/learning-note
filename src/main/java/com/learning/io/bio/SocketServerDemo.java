package com.learning.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: SocketServerDemo
 * @Author: sanwu
 * @Date: 2020/10/24 17:00
 */
public class SocketServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1112);
        Socket socket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        while (true) {
            System.out.println("start to read~");
            String read = in.readLine();
            System.out.println(read);
            out.write("server get Message " + read);
            out.flush();
        }

    }
}
