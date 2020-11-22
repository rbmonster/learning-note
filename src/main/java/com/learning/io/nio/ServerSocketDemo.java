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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * @Description:
 * NIO demo  与socket 交互
 * </pre>
 *
 * @version v1.0
 * @ClassName: ServerDemo
 * @Author: sanwu
 * @Date: 2020/10/25 11:04
 */
public class ServerSocketDemo {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1111));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        Selector clientReadSelector = Selector.open();
        Selector clientWriteSelector = Selector.open();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new ServerChannelThread(selector, clientReadSelector));
        executorService.submit(new ClientReadChannelThread(clientReadSelector, clientWriteSelector));
        executorService.submit(new ClientWriteChannelThread(clientReadSelector, clientWriteSelector));
    }

    /**
     * 一个服务器处理的demo 根据不同的通道事件类型处理不同的请求
     */
    static class ServerChannelThread implements Runnable {
        Selector serverSelector;
        Selector clientReadSelector;

        public ServerChannelThread(Selector serverSelector, Selector clientReadSelector) {
            this.serverSelector = serverSelector;
            this.clientReadSelector = clientReadSelector;
        }

        @Override
        public void run() {
            try {
                System.out.println("server channel start listen ~~~~~~~~~~~");
                while (true) {
                    // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                    if (serverSelector.select(1) > 0) {
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isAcceptable()) {
                                System.out.println(key.isReadable());
                                System.out.println(key.isWritable());
                                // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                clientChannel.configureBlocking(false);
                                // 这里可选择是否都由同一个选择器处理， 或者区分选择器去处理不同的channel event
                                // 这里统一使用clientReadSelector 处理读请求
                                clientChannel.register(clientReadSelector, SelectionKey.OP_READ);
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

    /**
     * 使用专门的选择器处理读事件
     */
    static class ClientReadChannelThread implements Runnable {
        Selector clientReadSelector;
        Selector clientWriteSelector;

        public ClientReadChannelThread(Selector clientReadSelector, Selector clientWriteSelector) {
            this.clientReadSelector = clientReadSelector;
            this.clientWriteSelector = clientWriteSelector;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                    if (clientReadSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientReadSelector.selectedKeys();
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
                                    clientChannel.register(clientWriteSelector, SelectionKey.OP_WRITE);
                                } finally {
                                    keyIterator.remove();
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

    static class ClientWriteChannelThread implements Runnable {
        Selector clientReadSelector;
        Selector clientWriteSelector;

        public ClientWriteChannelThread(Selector clientReadSelector, Selector clientWriteSelector) {
            this.clientReadSelector = clientReadSelector;
            this.clientWriteSelector = clientWriteSelector;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (clientWriteSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientWriteSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();

                            if (key.isWritable()) {
                                try {
                                    SocketChannel clientChannel = ((SocketChannel) key.channel());
                                    // 写操作
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    String msg = "server channel write: current time mill " + System.currentTimeMillis();
                                    byteBuffer.put(msg.getBytes());
                                    byteBuffer.flip();
                                    clientChannel.write(byteBuffer);
                                    clientChannel.register(clientReadSelector, SelectionKey.OP_READ);
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
