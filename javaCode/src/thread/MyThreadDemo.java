package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyThreadDemo {

    public static void main(String[] args) {

        ReentrantLock rl = new ReentrantLock();
        Semaphore semaphore = new Semaphore(5);

        Object lock = new Object();
        MyThread m1 = new MyThread(lock, rl, semaphore);
        MyThread m2 = new MyThread(lock, rl, semaphore);

        for (int i = 0; i < 5; i++) {
            new MyThread(lock, rl, semaphore).start();
        }

        m1.start();
      /*  try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        m2.start();

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        for (int j = 0; j < 5; j++) {
            Thread thread = new RWThread(readWriteLock);
            thread.setName("我是" + j + "号线程");
            thread.start();
        }


    }

}
