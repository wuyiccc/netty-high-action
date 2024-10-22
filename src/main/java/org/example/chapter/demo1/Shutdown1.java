package org.example.chapter.demo1;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Shutdown1 {

    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            System.out.println("ShutdownHook execute start...");
            System.out.println("Netty NioEventLoopGroup shutdownGracefully...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ShutdownHook execute end...");
        }, ""));
        TimeUnit.SECONDS.sleep(30);
        System.out.println("程序准备退出");
        System.exit(0);
    }
}
