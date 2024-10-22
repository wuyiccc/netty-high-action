package org.example.chapter.demo1;

import java.util.concurrent.TimeUnit;

/**
 * @author wuyiccc
 * @date 2024/10/22 20:29
 */
public class Test {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {

            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("子线程执行中");
            }

        });
        t.setDaemon(true);

        t.start();
        System.out.println("主线程执行完毕");
    }
}
