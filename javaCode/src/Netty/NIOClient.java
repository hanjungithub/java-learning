package Netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NIOClient
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/13 11:11
 **/
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        //提供服务器端的IP和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要事件，客户端不会阻塞，可以做其他工作...");
            }
            //若果连接成功，就发送数据
            String str = "hello，NIO";
            //wrap()产生一个buffer
            ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
            //发送数据，将buffer数据写入channel
            socketChannel.write(byteBuffer);
            System.in.read();
        }
    }
}
