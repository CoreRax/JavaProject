package com.corerax;

/**
 * Created by chengchuan on 2015/12/25.
 */
public class ThreadDeadLock implements Runnable{

    private int id;
    private Object o1; // 所需的资源1
    private Object o2; // 所需的资源2
    private int sleep;

    public ThreadDeadLock(int id, Object o1, Object o2, int sleepTime){
        this.id = id;
        this.o1 = o1;
        this.o2 = o2;
        this.sleep = sleepTime;
    }

    @Override
    public void run() {
        System.out.println("线程"+this.id+"正在运行...");
        synchronized (o1){
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2){
                System.out.println("线程 " + id);
            }
        }
    }
}