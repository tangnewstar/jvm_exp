package performance;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2024/12/2
 */
public class JConsoleThreadWaitingTest {
    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) // 第41行
                    ;
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
//        createLockThread(new Object());
        br.readLine();
        Object obj = new Object();
    }

    /**
     * testBusyThread RUNNABLE
     * 会在空循环耗尽操作系统分配给它的执行时间，直到线程切换为止，这种等待会消耗大量的处理器资源。
     *
     * testLockThread WAITING
     * 处于正常的活锁等待中，只要lock对象的notify()或notifyAll()方法被调用，
     * 这个线程便能激活继续执行。
     */
}
