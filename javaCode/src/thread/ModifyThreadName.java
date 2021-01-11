package thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ModifyThreadName
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/11 13:52
 **/
public class ModifyThreadName {
    public static void main(String[] args) {
        MyThreadException myThreadException = new MyThreadException();
      /*  Thread t = new MyThread(5);
        t.setUncaughtExceptionHandler(myThreadException);
        t.start();*/
        MyThreadPool myThreadPool = new MyThreadPool(5,10,200, TimeUnit.SECONDS,new LinkedBlockingDeque<>(),new ThreadFactory(){ public Thread newThread(Runnable r) {
            return new Thread(r, "t_pl_pool_" + r.hashCode());
        }}, new ThreadPoolExecutor.DiscardOldestPolicy());
        for(int i=0;i<20;i++){
            MyThread1 t = new MyThread1(i);
            t.setUncaughtExceptionHandler(myThreadException);
            //t.setName("MyThread:"+i);
            myThreadPool.execute(t);
        }
    }

}
