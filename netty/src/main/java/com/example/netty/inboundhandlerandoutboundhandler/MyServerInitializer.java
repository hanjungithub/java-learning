package com.example.netty.inboundhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @ClassName MyServerInitializer
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/20 17:33
 **/
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //入栈的handler进行解码,MyByteToLongDecoder
        pipeline.addLast("decoder",new MyByteToLongDecoder());
        pipeline.addLast(new MyServerHandler());

    }
}
