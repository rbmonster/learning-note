package com.learning.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * 客户端基于Channel 读写通信的demo
 * </pre>
 *
 * @version v1.0
 * @ClassName: ClientDemo
 * @Author: sanwu
 * @Date: 2020/11/21 17:55
 */
public class ClientChannelDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(1111));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        Random random = new Random();
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            String inputStr = "client send message : " + System.currentTimeMillis() + " " + random.nextInt(1000000);
            buf.put(inputStr.getBytes());
            buf.flip();
            try {
                socketChannel.write(buf);
                while (buf.remaining() > 0) {
                    socketChannel.write(buf);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            buf.clear();

            TimeUnit.SECONDS.sleep(1);
            socketChannel.register(selector, SelectionKey.OP_READ);
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey sk = iterator.next();

                    if (sk.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) sk.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        clientChannel.read(byteBuffer);
                        byteBuffer.flip();
                        System.out.println(
                                Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                        clientChannel.register(selector, SelectionKey.OP_WRITE);
                    }
                    iterator.remove();
                }
                if (selector.selectedKeys().isEmpty()) break;
            }
        }
    }
}
