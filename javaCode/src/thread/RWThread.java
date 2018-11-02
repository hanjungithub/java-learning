package thread;

import java.util.concurrent.locks.ReadWriteLock;

public class RWThread extends Thread {

    private ReadWriteLock readWriteLock;

    public RWThread(ReadWriteLock readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void run() {
        myRead();
    }


    private void myRead() {

        readWriteLock.writeLock().lock();
        System.out.println(currentThread().getName() + ":我在写，等一下。。。");
        try {
            Thread.sleep(6000);
            System.out.println(currentThread().getName() + ":我写完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readWriteLock.writeLock().unlock();


        readWriteLock.readLock().lock();
        System.out.println(currentThread().getName() + ":我在读，等一下。。。");
        try {
            Thread.sleep(5000);
            System.out.println(currentThread().getName() + ":我读完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readWriteLock.readLock().unlock();


    }


}
