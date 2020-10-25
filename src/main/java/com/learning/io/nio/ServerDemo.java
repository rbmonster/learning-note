package com.learning.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * <pre>
 * @Description:
 * NIO demo
 * </pre>
 *
 * @version v1.0
 * @ClassName: ServerDemo
 * @Author: sanwu
 * @Date: 2020/10/25 11:04
 */
public class ServerDemo {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1111));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        Selector clientSelector = Selector.open();
        new Thread(new ServerChannelThread(selector, clientSelector)).start();
//        new Thread(new ClientChannelThread(clientSelector)).start();
    }

    /**
     * 一个服务器处理的demo 根据不同的通道事件类型处理不同的请求
     */
    static class ServerChannelThread implements Runnable {
        Selector serverSelector;
        Selector clientSelector;

        public ServerChannelThread(Selector serverSelector, Selector clientSelector) {
            this.serverSelector = serverSelector;
            this.clientSelector = clientSelector;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                    if (serverSelector.select(1) > 0) {
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isAcceptable()) {
                                try {
                                    // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    // 这里可选择是否都由同一个选择器处理， 或者区分选择器去处理不同的channel event
                                    clientChannel.register(serverSelector, SelectionKey.OP_READ);
                                } finally {
                                    // 连接请求处理完成 需要移除
                                    keyIterator.remove();
                                }
                            } else if (key.isReadable()) {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                // (3) 面向 Buffer
                                clientChannel.read(byteBuffer);
                                byteBuffer.flip();
                                System.out.println(
                                        Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                            } else if(key.isWritable()) {
                                SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                // 写操作
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                byteBuffer.put("server channel write~".getBytes());
                                clientChannel.write(byteBuffer);

                            }
//                            keyIterator.remove();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 使用专门的选择器处理读事件
     */
    static class ClientChannelThread implements Runnable {
        Selector clientSelector;

        public ClientChannelThread(Selector clientSelector) {
            this.clientSelector = clientSelector;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                    if (clientSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();

                            if (key.isReadable()) {
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    // (3) 面向 Buffer
                                    clientChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(
                                            Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                } finally {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }

                        }
                    }

                }
            } catch (CharacterCodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
