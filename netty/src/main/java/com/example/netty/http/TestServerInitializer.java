package com.example.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @ClassName TestServerInitializer
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/18 12:37
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //netty提供的http的编解码器
        pipeline.addLast("myHttpServerCodec",new HttpServerCodec());
        //增加一个自定义的handler
        pipeline.addLast("myHttpServerHandler",new TestHttpServerHandler());

    }
}
