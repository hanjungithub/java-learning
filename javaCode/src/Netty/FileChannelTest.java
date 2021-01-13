package Netty;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @ClassName FileChannelTest
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/11 16:15
 **/
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        //1.写文件
        /*FileOutputStream fileOutputStream = new FileOutputStream("d:\\helloFileChannel.txt");
        //把字符串写到放文件里
        String str = "hello,FileChannel";
        //把 字符串写到 buffer了中
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        // 把 buffer 缓冲区的数据 写到FileChannel中去
        FileChannel fileChannel = fileOutputStream.getChannel();
        //写到 fileChannel中去
        fileChannel.write(byteBuffer);
        fileOutputStream.close();*/
        //2.读文件
        /*File file = new File("d:\\helloFileChannel.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        int read = fileChannel.read(byteBuffer);
        System.out.print(new String(byteBuffer.array()));
        fileInputStream.close();*/
        //3.拷贝文件
        /*File file = new File("d:\\helloFileChannel.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        File file1 = new File("d:\\helloFileChannel1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        FileChannel fileChannel1 = fileOutputStream.getChannel();
        while (true){
            byteBuffer.clear();
            int read = fileChannel.read(byteBuffer);
            if(read==-1){
                break;
            }
            byteBuffer.flip();
            fileChannel1.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();*/
        //4.transferTo拷贝
        /*File file = new File("d:\\helloFileChannel1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        File file1 = new File("d:\\helloFileChannel2.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        FileChannel fileChannel1 = fileOutputStream.getChannel();
        fileChannel.transferTo(0,file.length(),fileChannel1);*/
        //5.transferFrom拷贝
        File file = new File("d:\\helloFileChannel1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        File file1 = new File("d:\\helloFileChannel5.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        FileChannel fileChannel1 = fileOutputStream.getChannel();
        fileChannel1.transferFrom(fileChannel,0,file.length());
    }
}
