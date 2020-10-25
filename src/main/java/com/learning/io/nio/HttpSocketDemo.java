package com.learning.io.nio;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

/**
 * <pre>
 * @Description:
 * 模拟网络请求， 单通道进行请求的模拟和响应
 * </pre>
 *
 * @version v1.0
 * @ClassName: HttpSocketDemmo
 * @Author: sanwu
 * @Date: 2020/10/25 14:39
 */
public class HttpSocketDemo {
    private static Charset charset = Charset.forName("UTF8");

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1111));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        HttpClient client = HttpClientBuilder.create().build();


        while (true) {
            // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
            if (selector.select(1) > 0) {
                System.out.println("============>");
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = set.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                        clientChannel.configureBlocking(false);
                        // 这里可选择是否都由同一个选择器处理， 或者区分选择器去处理不同的channel event
                        clientChannel.register(selector, SelectionKey.OP_WRITE);
                    } else if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        clientChannel.read(byteBuffer);
                        byteBuffer.flip();
                        System.out.println(
                                Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                        key.interestOps(SelectionKey.OP_WRITE);//修改监听模式为WRITE
                        selector.wakeup();// 唤醒IO线程
                    } else if (key.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        // 写操作
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        String data = executeResponse(client).substring(0, 100);
                        byteBuffer.put(data.getBytes());
                        clientChannel.write(byteBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                        selector.wakeup();// 唤醒IO线程
                    }
                    keyIterator.remove();
                }
            }
        }

    }

    private static String executeResponse(HttpClient client) throws IOException {
        String data = "";
        // 根据URL创建HttpGet实例
        HttpGet get = new HttpGet("http://www.baidu.com");
        // 执行get请求，得到返回体
        HttpResponse response = client.execute(get);
        // 判断是否正常返回
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 解析数据
            data = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
        }

        return data;
    }
}
