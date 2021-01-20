package com.example.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyLongToByteEncoder
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/20 17:45
 **/
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("MyLongToByteEncoder encode方法被调用");
        System.out.println("msg = " + msg);
        out.writeLong(msg);
    }
}
