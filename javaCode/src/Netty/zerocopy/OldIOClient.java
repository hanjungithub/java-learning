package Netty.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @ClassName OldIOClient
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/14 15:08
 **/
public class OldIOClient {
    public static void main(String[] args) throws IOException {
        Socket  socket = new Socket("localhost",7001);
        String file = "d:\\SocketPro.zip";
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] byteArray = new byte[4096];
        long readCount;
        long total =0;
        long start = System.currentTimeMillis();
        while ((readCount= fileInputStream.read(byteArray))>=0){
            total+=readCount;
            socket.getOutputStream().write(byteArray);
        }
        System.out.println("发送总字节数："+total + ",耗时："+(System.currentTimeMillis()-start) + "毫秒");
        socket.getOutputStream().close();
        socket.close();
        fileInputStream.close();
    }

}
