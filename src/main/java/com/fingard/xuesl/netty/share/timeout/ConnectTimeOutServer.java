package com.fingard.xuesl.netty.share.timeout;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Slf4j
public class ConnectTimeOutServer {
    private static AtomicInteger outCount = new AtomicInteger(0);
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        ((NioEventLoopGroup) bossGroup).setIoRatio(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                //TODO 演示一下BACKLOG的作用
//                .option(ChannelOption.SO_BACKLOG, 2)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                log.info("新连接进入");
                            }

                            @Override
                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                outCount.incrementAndGet();
                                log.info("连接退出");
                            }
                        });
                    }
                });
        bind(serverBootstrap, 8080);
        new Thread(()-> {
            while(true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("连接失败:" + outCount.get());
            }
        }).start();
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(channelFuture -> {
            if (channelFuture.isSuccess()) {
                log.info("服务器在端口[" + port + "]绑定成功!");
            } else {
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
