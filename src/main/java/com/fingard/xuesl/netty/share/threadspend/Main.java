package com.fingard.xuesl.netty.share.threadspend;

/**
 * @author xuesl
 * @date 2018/12/10
 */
public class Main {
    public static void main(String[] args) {
        int count = Integer.valueOf(args[0]);
        for (int i = 0; i<count; i++) {
            new Thread(()->{
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "threadspend-" + i).start();
        }
        System.out.println("线程启动完毕!共启动" + count + "个线程");
    }
}
