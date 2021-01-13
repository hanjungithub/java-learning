package Netty;

import com.sun.org.apache.bcel.internal.generic.Select;
import jdk.jfr.events.SocketReadEvent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NIOServer
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/13 10:44
 **/
public class NIOServer {
    public static void main(String[] args) throws IOException {
        //服务端channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //创建一个selector 对象
        Selector selector = Selector.open();
        //监听6666端口
        serverSocketChannel.bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel注册到selector上,关注OP_ACCEPT事件,这里是监听服务端的通道的ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true) {
            //通过selector监听事件OP_ACCEPT类型,如果等待1秒还没有OP_ACCEPT事件发生就返回，这里是判断服务端通道有没有ACCEPT事件
            if (selector.select(1000) == 0) {
                System.out.println("等待了1秒，没有事件发生，返回");
                continue;
            }
            //服务端channel监听到了ACCEPT事件后往下走
            //如果返回的不是0，就是有事件发生，获取selectionKeys集合,可以通过selectionKeys反向获取channel
            final Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeysIterator = selectionKeys.iterator();
            int i=0;
            while (selectionKeysIterator.hasNext()) {
                System.out.println("进来了"+(++i)+"次");
                final SelectionKey selectionKey = selectionKeysIterator.next();
                //通过selectionKey对应的通道发生的事件来做对应的处理
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个socketChannel:" + socketChannel.hashCode());
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将当前的channel也注册到selector上
                    //这里是将客户端channel注册到selector上，并关注了READ事件，
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //如果发生的是读取事件
                if (selectionKey.isReadable()) {
                    //通过selectionKey反向获取到对应的channel
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //获取到该channel关联的buffer,attachment()方法获取buffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端：" + new String(byteBuffer.array()));
                }
                //手动从集合中删除当前的selectionKey，防止重复操作
                selectionKeysIterator.remove();
            }


        }
    }
}
