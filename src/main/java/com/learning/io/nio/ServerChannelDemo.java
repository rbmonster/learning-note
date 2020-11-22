package com.learning.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * <pre>
 * @Description:
 * server 基于通道读写通信的demo
 * </pre>
 *
 * @version v1.0
 * @ClassName: ServerChannelDemo
 * @Author: sanwu
 * @Date: 2020/11/21 18:34
 */
public class ServerChannelDemo {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1111));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 一个选择器负责处理所有事件
        try {
            while (true) {
                // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                if (selector.select(1) > 0) {
                    Set<SelectionKey> set = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = set.iterator();

                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        if (key.isAcceptable()) {
                            // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                            SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                            clientChannel.configureBlocking(false);
                            // 这里可选择是否都由同一个选择器处理， 或者区分选择器去处理不同的channel event
                            clientChannel.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            SocketChannel clientChannel = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            // (3) 面向 Buffer
                            clientChannel.read(byteBuffer);
                            byteBuffer.flip();
                            System.out.println(
                                    Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                            clientChannel.register(selector, SelectionKey.OP_WRITE);
                        } else if (key.isWritable()) {
                            SocketChannel clientChannel = ((SocketChannel) key.channel());
                            // 写操作
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            String msg = "server channel write: current time mill" + System.currentTimeMillis();
                            byteBuffer.put(msg.getBytes());
                            byteBuffer.flip();
                            clientChannel.write(byteBuffer);
                            clientChannel.register(selector, SelectionKey.OP_READ);
                        }
                        keyIterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
