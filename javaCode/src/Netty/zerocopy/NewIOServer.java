package Netty.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NewIOServer
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/14 15:16
 **/
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);
        serverSocketChannel.socket().bind(inetSocketAddress);
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount =0;
            try {
                readCount = socketChannel.read(byteBuffer);
            }catch (Exception e){
                e.printStackTrace();
            }
            byteBuffer.rewind();
        }
    }
}
