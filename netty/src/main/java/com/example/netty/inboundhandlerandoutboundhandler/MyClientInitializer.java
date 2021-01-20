package com.example.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

import java.nio.channels.SocketChannel;

/**
 * @ClassName MyClientInitializer
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/20 17:43
 **/
public class MyClientInitializer extends ChannelInitializer<io.netty.channel.socket.SocketChannel> {


    @Override
    protected void initChannel(io.netty.channel.socket.SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //加入一个出站的handler,对数据进行编码
        pipeline.addLast("encoder",new MyLongToByteEncoder());
        //加入一个自定义handler，处理业务
        pipeline.addLast(new MyClientHandler());
    }
}
