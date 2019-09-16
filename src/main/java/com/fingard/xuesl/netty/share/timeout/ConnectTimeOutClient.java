package com.fingard.xuesl.netty.share.timeout;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Slf4j
public class ConnectTimeOutClient {
    private static AtomicInteger errorCount = new AtomicInteger(0);
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                    }
                });
        for (int i = 0;i<10000;i++) {
            bootstrap.connect("127.0.0.1", 8080).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        log.info("连接成功！" + channelFuture.channel().localAddress());
                    } else {
                        errorCount.incrementAndGet();
                        log.error(channelFuture.cause().getMessage());
                    }
                }
            });
        }
        new Thread(()-> {
            while(true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("连接失败:" + errorCount.get());
            }
        }).start();
    }
}
