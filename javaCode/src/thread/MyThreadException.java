package thread;

public class MyThreadException implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("=========================="+e.getMessage());
    }
}