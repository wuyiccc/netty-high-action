package org.example.chapter.demo1;

import java.util.concurrent.TimeUnit;

/**
 */
public class DaemonT1 {
    public static void main(String[] args)
            throws IllegalArgumentException, InterruptedException {
        long startTime = System.nanoTime();
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.DAYS.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();}}
        }, "Daemon-T");
        t.setDaemon(false);
        t.start();
        TimeUnit.SECONDS.sleep(15);
        System.out.println("系统退出, 程序执行" +(System.nanoTime() - startTime)/1000/1000/1000 + " s");}
}
