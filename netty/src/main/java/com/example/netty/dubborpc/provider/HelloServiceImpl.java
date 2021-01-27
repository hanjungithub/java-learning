package com.example.netty.dubborpc.provider;

import com.example.netty.dubborpc.publicinterface.HelloService;

/**
 * @ClassName HelloServiceImpl
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/21 11:41
 **/
public class HelloServiceImpl implements HelloService {
    private int count = 0;
    //当有消费方低啊用该方法时，就返回一个结果
    @Override
    public String hello(String mes) {
        System.out.println("收到客户端消息=" + mes);
        //根据mes，返回不同的结果
        if(mes!=null){
            return "你好客户端，我已经收到你的消息[" + mes + "]" + "第" + (++count) + "次";
        }else{
            return "你好客户端，我已经收到你的消息";
        }
    }
}
