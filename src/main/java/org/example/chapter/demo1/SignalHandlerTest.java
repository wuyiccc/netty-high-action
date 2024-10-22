package org.example.chapter.demo1;

import sun.misc.Signal;

import java.util.concurrent.TimeUnit;

/**
 * signal 负责提前拦截信号进行处理, 如果处理逻辑没有jvm退出操作, 那么jvm就不会退出, 必须在signal里面手动System.exit(0), jvm才会退出, 然后才会执行shutdownHook,
 * 所以当signal和shutdownHook同时存在的时候, signal会拦截信号, 如果signal逻辑中没有System.exit(0), 那么就不会退出jvm, shutdownHook就不会执行
 */
public class SignalHandlerTest {

    public static void main(String[] args) throws Exception {


        // linux term == kill -15 发出软件终止信号, 可能会被signal监听器打断
        Signal sig = new Signal("INT");

        // 将实例化的SignalHandler注册到jdk的Signal
        Signal.handle(sig, (s) -> {
            // 接收到对应的信号之后回调
            System.out.println("Signal handle start...");
            //try {
            //    //TimeUnit.SECONDS.sleep(10);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            // kll -15 (TERM) 执行发出的时候 当signal和shutdownHook同时存在的时候, 必须在signal中手动调用exit, 否则只会执行signal, 不会执行shutdownHook, jvm就一直不会退出
            System.exit(0);
        });

        // kill -2
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

        new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.DAYS.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Daemon-T").start();
    }
}
