package Netty;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @ClassName ByteBufferArrayTest
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/12 15:31
 **/
public class ByteBufferArrayTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
        serverSocketChannel.bind(inetSocketAddress);
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        //类似QQ发消息，有最大长度
        int maxByteReadLength =10;
        int byteRead = 0;
        while (true){
            long read = socketChannel.read(byteBuffers);
            byteRead +=read;
            System.out.println("byteRead="+byteRead);
            //看一下缓冲区的情况
            Arrays.asList(byteBuffers).stream().map(byteBuffer->"position="+ byteBuffer.position()+",limit="+byteBuffer.limit()).forEach(System.out::println);
            //这行判断可以当buffer满了再去写文件
            //if(byteRead>=byteBuffers[0].capacity() + byteBuffers[1].capacity()) {
                //把内容后台输出一下
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> new String(byteBuffer.array())).forEach(System.out::print);
                System.out.println();
                Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
                //写到文件里
                FileOutputStream fileOutputStream = new FileOutputStream("d:\\helloFileChannel.txt",true);
                FileChannel fileChannel = fileOutputStream.getChannel();
                //写到 fileChannel中去
                fileChannel.write(byteBuffers);
                fileOutputStream.close();
                Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());
                //写完文件，重置读取到的数据长度
                byteRead = 0;
           // }

        }

    }
}
