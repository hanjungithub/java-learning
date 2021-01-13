package Netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @ClassName BioTest
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/11 12:06
 **/
public class BioTest {
    
    
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Socket服务端已经启动，端口号为6666");
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("接收到一个客户端");
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                private void handler(Socket socket) throws IOException {
                    InputStream inputStream = socket.getInputStream();
                    while (true){
                        byte[] bytes = new byte[1024];
                        int read = inputStream.read(bytes);
                        if(read!=-1){
                            System.out.println(new String(bytes,0,read));
                        }else{
                            break;
                        }
                    }
                }
            });
        }

    }
}
