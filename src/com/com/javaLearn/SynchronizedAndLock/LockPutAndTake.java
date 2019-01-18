package com.javaLearn.SynchronizedAndLock;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockPutAndTake {

    final Lock lock = new ReentrantLock();
    final Condition putCondition = lock.newCondition();//生产队列
    final Condition takeCondition = lock.newCondition();//消费队列
    final Map<String,Object>[] container = new Map[10];//固定最大数量的容器
    Integer currentCount = 0;
    public static void main(String[] args) {
       LockPutAndTake lockPutAndTake = new LockPutAndTake();
       for(int i =0 ;i<5;i++){

           Thread takeThread = new Thread(new Runnable() {
               @Override
               public void run() {
                   try {
                       lockPutAndTake.take();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           },"thread-take-"+i) ;
           takeThread.start();

           Thread putThread = new Thread(new Runnable() {
              @Override
              public void run() {
                  try {
                      lockPutAndTake.put();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }

              }
              },"thread-put-"+i) ;
            putThread.start();



       }
       //System.out.println(lockPutAndTake.container.length);
       //System.out.println(lockPutAndTake.currentCount);
    }

    private void put() throws InterruptedException {


        lock.lock();
        System.out.println(Thread.currentThread().getName()+"-begin");
        try{
            while (currentCount == container.length){
                //将当前线程移入生产等待队列,待其他线程执行signal或signalAll之后将当前线程移入lock-sync队列
                putCondition.await();
            }
            currentCount++;
            Map<String,Object> obj = new HashMap<>();
            obj.put("key",currentCount);
            container[currentCount] = obj;
            //将takeCondition消费队列中的一个线程放入lock队列
            takeCondition.signal();
        }finally {
            //释放锁
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+"-finish");


    }

    private void take() throws InterruptedException {

        lock.lock();
        System.out.println(Thread.currentThread().getName()+"-begin");
        try{
            while (currentCount == 0){
                //将当前线程移入消费等待队列，待其他线程执行signal或signalAll之后将当前线程移入lock-sync队列
                takeCondition.await();
            }
            currentCount --;
            ArrayUtils.remove(container,currentCount);
            //将putCondition生产队列中的一个线程放入lock队列
            putCondition.signal();

        }finally {
            //释放锁
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+"-finish");
    }

}
