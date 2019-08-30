package com.example.redis;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Message
 * @Description 消息体
 * @Author hanjun
 * @Date 2019/8/30 14:27
 **/
@Setter
@Getter
public class Message implements Serializable {

    private static final long serialVersionUID = -835030387500089522L;
    private String content;
    private Date time;

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(this.time);
        return "Message{" +
                "content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
