package com.example.netty.dubborpc.provider;

import com.example.netty.dubborpc.netty.NettyServer;

/**
 * @ClassName ServerBootstrap
 * @Description 启动一个服务提供者，就是NettyServer
 * @Author hanjun
 * @Date 2021/1/21 11:45
 **/
public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("localhost",7000);

    }
}
