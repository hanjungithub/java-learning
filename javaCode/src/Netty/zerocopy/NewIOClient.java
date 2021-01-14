package Netty.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NewIOClient
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/14 15:21
 **/
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(7001));
        FileInputStream fileInputStream = new FileInputStream("d:\\SocketPro.zip");
        FileChannel fileChannel = fileInputStream.getChannel();
        long start = System.currentTimeMillis();
        //在linux下一个transferTo方法就可以完成传输
        //在windows下一次调用transferTo方法只能发送8M,就需要分段传输文件，
        //transferTo 底层使用的零拷贝
        long transferCount = fileChannel.transferTo(0,fileChannel.size(),socketChannel);
        System.out.println("发送总字节数："+transferCount + ",耗时："+(System.currentTimeMillis()-start) + "毫秒");
        fileChannel.close();
    }
}
