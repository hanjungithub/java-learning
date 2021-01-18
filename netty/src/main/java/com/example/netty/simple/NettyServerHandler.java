package com.example.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServerHandler
 * @Description 1.自定义一个Handler需要继承netty规定好的某个handlerAdapter（规范）
 *              2.这是我们自定义一个handler，才能称为一个handler
 * @Author hanjun
 * @Date 2021/1/15 11:23
 **/

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据（这里我们可以读取客户端发送的消息）
     * @param ctx 上下文对象，含有 管道pipeline，通道channel，地址
     * @param msg 客户端发送的数据 默认object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        //将msg转成一个ByteBuf --netty提供的和Nio的ByteBuffer不一样
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址："+ctx.channel().remoteAddress());
        //TaskQueue和scheduleTaskQueue测试，两个队列应该是不相关的，这边一定要等task的任务执行完了再执行schedule可能是因为执行Thread.sleep方法，导致整个线程休眠了
        //2.用户自定义定时任务 scheduleTaskQueue
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                /* try {*/
                //Thread.sleep(10*1000);
                System.out.println("schedule");
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello2,测试延迟10秒执行的用户自定义定时任务加入到TaskQueue中，异步执行~",CharsetUtil.UTF_8));
                /*} catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        },1, TimeUnit.SECONDS);

        //1.用户自定义任务 taskQueue
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3*1000); //
                    System.out.println("task");
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,测试用户自定义任务加入到TaskQueue中，异步执行~",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        /*ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10*1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello1,测试用户自定义任务加入到TaskQueue中，异步执行~",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/



        System.out.println("go on ...");

    }

    /**
     * 客户端数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //write+flush,将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端~",CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，一般是需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
