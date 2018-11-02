package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {

    private Object lock;
    private ReentrantLock rl;
    private Semaphore semaphore;

    public MyThread(Object lock, ReentrantLock rl, Semaphore semaphore) {
        this.lock = lock;
        this.rl = rl;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        print();
    }

    private void print() {
        //synchronized (lock){
        //System.out.println(rl.isLocked());
        /*try {
            if(!rl.tryLock(2,TimeUnit.SECONDS)){
                System.out.println("2秒还没得到锁，我放弃了。。。");
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //rl.lock();
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(currentThread().getName() + ":执行了。。。");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            /*rl.unlock();
            System.out.println("释放了："+rl.isLocked());*/
            semaphore.release();
        }
        //}
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }
}
