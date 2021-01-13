package Netty.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @ClassName GroupChatServer
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/13 15:25
 **/
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        //得到选择器
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.bind(new InetSocketAddress(PORT));
            //设置非阻塞模式
            listenChannel.configureBlocking(false);
            //将改listenChannel注册到selector上
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);  //注册的channel放到keys()集合中
            //System.out.println(selector.keys().size() + "-----" +selector.selectedKeys().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //监听
    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000); //就绪的key在selector.selectedKeys()，用完之后要移除
                //System.out.println(selector.keys().size() + "-----" +selector.selectedKeys().size());
                //如果count>0代表有事件要处理
                if (count > 0) {
                    //遍历selectionKeys
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while(iterator.hasNext()){
                        //取出selectionKey
                        SelectionKey key = iterator.next();
                        if(key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            //将sc注册到selector
                            sc.register(selector,SelectionKey.OP_READ);

                            //提示
                            System.out.println(sc.getRemoteAddress() + ":"+ " 上线 ");
                        }
                        if(key.isReadable()){
                            //处理读
                            readData(key);
                        }
                        //当前的key删除，防止重复处理
                        // 注解： selectionKey是请求过来的一个带事件状态的channel，不从selector.selectedKeys()集合中移除，
                        // 下次另一个就绪状态的channel进来了，之前那个还会被得到，但是已经处理完了就为null了

                        /** stack overflow上 对于为什么要对selector.selectedKeys()集合中移除的解释
                         *
                         * There are 2 tables in the selector:
                         *
                         * registration table: when we call channel.register, there will be a new item(key) into it. Only if we call key.cancel(), it will be removed from this table.
                         *
                         * ready for selection table: when we call selector.select(), the selector will look up the registration table, find the keys which are available, copy the references of them to this selection table. The items of this table won't be cleared by selector(that means, even if we call selector.select() again, it won't clear the existing items)
                         *
                         * That's why we have to invoke iter.remove() when we got the key from selection table. If not, we will get the key again and again by selector.selectedKeys() even if it's not ready to use.
                         */


                        iterator.remove();
                    }
                } else {
                    //System.out.println("等待...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    //读取客户端消息
    public void readData(SelectionKey key){
        SocketChannel channel = null;
        try {
            //定义一个socketChannel
            channel = (SocketChannel)key.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if(count>0){
                //把缓冲区的数据转成字符串
                String msg = new String(buffer.array());
                //服务端输出消息
                System.out.println("from 客户端：" + msg);
                //向其他客户端转发消息，写一个方法处理
                sendInfoToOtherClient(msg,channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + ":" + channel.hashCode() + " 离线了... ");
                key.cancel();
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    //转发消息
    private void sendInfoToOtherClient(String msg,SocketChannel self){
        System.out.println("服务器转发消息");
        //遍历所有注册到selector上的socketChannel，并排除self
        for(SelectionKey key:selector.keys()){
            //取出通道
            Channel targetChannel = key.channel();
            //排除自己
            if(targetChannel instanceof SocketChannel && targetChannel!=self){
                //转型
                targetChannel = (SocketChannel) targetChannel;
                //将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                try {
                     ((SocketChannel) targetChannel).write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
