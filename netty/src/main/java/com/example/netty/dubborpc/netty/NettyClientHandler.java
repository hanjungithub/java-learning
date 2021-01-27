package com.example.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @ClassName NettyClientHandler
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/21 12:06
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context;
    private String result;
    private String para;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //在其他方法会使用到ctx
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        //唤醒等待的线程
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public synchronized Object call() throws Exception {
        //被代理对象调用，发送数据给服务器，-> wait ，等待被唤醒 ->返回结果
        context.writeAndFlush(para);
        wait(); //等待channelRead唤醒
        return result;
    }

    void setPara(String para) {
        this.para = para;
    }
}
