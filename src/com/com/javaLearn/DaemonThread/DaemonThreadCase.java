package com.javaLearn.DaemonThread;

/*
    DaemonThread:守护线程测试类
    参考：https://blog.csdn.net/u010739551/article/details/51065923
 */
public class DaemonThreadCase {
    //在Java中有两类线程：User Thread(用户线程)、Daemon Thread(守护线程)
    /*
        只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
        Daemon的作用是为其他线程的运行提供便利服务，守护线程最典型的应用就是 GC (垃圾回收器)
        User和Daemon两者几乎没有区别，唯一的不同之处就在于虚拟机的离开：如果 User Thread已经全部退出运行了，只剩下Daemon Thread存在了，虚拟机也就退出了。
        因为没有了被守护者，Daemon也就没有工作可做了，也就没有继续运行程序的必要了
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(new NormalTask());
        Thread t2 = new Thread(new DaemonTask());
        /*
            将t2设为守护线程。 thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程
            在Daemon线程中产生的新线程也是Daemon的
         */
        t2.setDaemon(true);
        t1.start();
        t2.start();
    }

}

class NormalTask implements Runnable{

    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            System.out.println("run-normal-thread-"+i);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DaemonTask implements Runnable{

    @Override
    public void run() {
        for(int i = 0;i<9999;i++){
            System.out.println("run-daemon-thread-"+i);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}