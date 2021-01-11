package thread;

public class MyThread1 extends Thread{

    public int i ;

    public MyThread1(int i){
        this.i=i;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("MyThread#"+i); //修改名称有用
        if(i%5==0) try {
            throw new Exception("第"+i+"个线程异常：");
        } catch (Exception e) {
            e.printStackTrace();
        }
         System.out.println(Thread.currentThread().getName()+"++++"+i+":running...");
    }
}