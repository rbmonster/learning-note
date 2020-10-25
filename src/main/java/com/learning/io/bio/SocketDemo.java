package com.learning.io.bio;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: SocketDemo
 * @Author: sanwu
 * @Date: 2020/10/24 16:39
 */
public class SocketDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        String filename = "application.yml";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(path+ File.separator+filename)));
        StringBuilder sb = new StringBuilder();
        fileReader.lines().forEach(line -> sb.append(line));

        PrintWriter out = null;
        BufferedReader networkIn = null;
        try{
            //创建socket连接
            Socket theSocket = new Socket("localhost", 1112);
            //通过缓存读取socket的输入流
            networkIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
            //读取系统控制台的输入流
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            //创建socket的写入流
            out = new PrintWriter(theSocket.getOutputStream());
            System.out.println("connect to echo server!!");

            while (true)
            {
                //读取控制台每行输入
                String theLine = userIn.readLine();
                if (theLine.equals(".")) break;
                //写入socket的
                out.println(theLine);
                //清空写入缓存
                out.flush();
                //读取socket 的写入的字符，按行读取。
//                System.out.println(networkIn.readLine());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if (networkIn != null)  networkIn.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
