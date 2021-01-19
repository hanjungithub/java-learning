package com.example.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyServer
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/19 11:43
 **/
public class MyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //加入一个netty 提供IdleStateHandler
                            ChannelPipeline pipeline = ch.pipeline();
                            /**
                             * 说明
                             * 1.是netty提供的处理空闲状态的处理器
                             * 2.readerIdleTime：表示多长时间没有读，就会发送一个心跳检测包，检测是否还是连接的状态
                             * 3.writerIdleTime: 表示多长时间没有写，就会发送一个心跳检测包，检测是否还是连接的状态
                             * 4.allIdeTime: 表示多长时间没有读写，就会发送一个心跳检测包，检测是否还是连接的状态
                             * 5.当IdleStateEvent事件触发后，就会传递给管道的下一个handler去处理，
                             * 通过调用（触发）下一个handler的userEventTriggered，在该方法中去处理
                             */
                            pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                            pipeline.addLast(new MyServerHandler());
                        }
                    });
            System.out.println("netty 服务器启动");
            ChannelFuture channelFuture = b.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
