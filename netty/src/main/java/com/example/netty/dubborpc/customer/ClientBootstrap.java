package com.example.netty.dubborpc.customer;

import com.example.netty.dubborpc.netty.NettyClient;
import com.example.netty.dubborpc.publicinterface.HelloService;

/**
 * @ClassName ClientBootstrap
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/21 12:49
 **/
public class ClientBootstrap {
    public static final String providerName = "HelloService#hello#";
    public static void main(String[] args) throws InterruptedException {
        NettyClient customer = new NettyClient();
        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for(;;) {
            String res = service.hello("你好，dubbo~");
            System.out.println("调用的结果 res= " + res);
            Thread.sleep(10000);
        }
    }
}
