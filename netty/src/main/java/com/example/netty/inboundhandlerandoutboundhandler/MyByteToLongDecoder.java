package com.example.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName MyByteToLongDecoder
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/20 17:35
 **/
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     * @param ctx 上下文
     * @param in  入栈的ByteBuf
     * @param out List集合，将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //因为long是8个字节,需要判断有8个字节，才能读取一个long
        if (in.readByte() >= 8) {
            out.add(in.readLong());
        }
    }
}
