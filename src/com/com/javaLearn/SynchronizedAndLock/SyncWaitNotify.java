package com.javaLearn.SynchronizedAndLock;

import java.util.concurrent.TimeUnit;

/*
* synchronized可以保证方法或代码块在运行时，同一时刻只有一个线程可以进入到临界区（互斥性），同时它还保证了共享变量的内存可见性。
*
*
* */
public class SyncWaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread waitThread = new Thread(new Wait(),"wait-thread");
        waitThread.start();
        //当前线程休眠2秒
        TimeUnit.SECONDS.sleep(2);
        Thread notifyThread = new Thread(new Notify(),"notify-thread");
        notifyThread.start();

    }
    //需在STATIC MAIN中实例化Wait&Notify，如果在外部类的静态方法中实例化内部类，内部类只能为静态内部类。
    //使用wait()、notify()和notifyAll()时需要先对调用对象加锁
    /*初始(NEW)：新建一个线程的对象，还未调用start方法
    运行(RUNNABLE)：java线程中将已经准备就绪(Ready)和正在运行中(Running)的两种状态都统称为“Runnable”。准备就绪的线程会被放在线程池中等待被调用
    阻塞(BLOCKED)：是因为某种的原因而放弃了CPU的使用权，暂时的停止了运行。直到线程进入准备就绪(Ready)状态才会有机会转到运行状态
    等待(WAITING)：该状态的线程需要等待其他线程做出一些特定的动作（通知或者是中断）
    超时等待(TIME_WAITING)：该状态和上面的等待不同，他可以在指定的时间内自行返回
    终止(TERMINATED)：线程任务执行完毕*/

    static class Wait implements Runnable{

        @Override
        public void run() {
            //同步代码块，锁是括号中的对象。对lock对象加锁，只有当
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread() + " flag is true");
                        //调用wait()方法后会释放锁，当前线程进入等待队列（WAITING QUEUE）。
                        // 只有当其他加锁线程执行notify()或notifyAll()并释放锁之后，才有机会将当前线程移动至阻塞队列（BLOCKED QUECE）
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false");
            }

        }
    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread()+"start");
            //对象锁
            synchronized (lock){
                flag = false;
                // 线程Notify调用对象lock的notifyAll()方法，线程Wait收到通知后从wait方法处返回继续执行，线程Notify对共享变量flag的修改对线程Wait来说是可见的。
                // notify()或notifyAll()方法调用后，等待线程不会立刻从wait()中返回，需要等该线程释放锁(代码块执行完毕)之后，才有机会获取锁之后从wait()返回
                lock.notifyAll();

                try {

                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"finish");
            }
        }
    }

}
