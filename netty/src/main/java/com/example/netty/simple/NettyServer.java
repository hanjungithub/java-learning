package com.example.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName NettyServer
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/15 11:04
 **/
public class NettyServer {
    public static void main(String[] args) throws Exception{
        //创建BossGroup和WorkerGroup
        //说明
        //1.创建两个现场组bossGroup和workerGroup
        //2.bossGroup只是处理连接请求，真正和客户端业务处理，会交给workerGroup完成
        //3.两个都是无限循环
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建服务器端的启动对象，配置参数
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程来进行设置
            //设置两个线程组
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)//使用NioSocketChannel作为服务器通道
                    .option(ChannelOption.SO_BACKLOG,128) //设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象（匿名对象）
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception{
                            //打印每个连接过来channel的信息
                            System.out.println("channel = " + ch.hashCode());
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    }); //给我们的workerGroup的EventLoop对应的管道设置处理器

            System.out.println("...服务器 is ready ...");
            //绑定一个端口并且同步，生成一个ChannelFuture对象,启动服务器（并绑定端口）
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
