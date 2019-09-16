package com.fingard.xuesl.netty.share.eventflow;

import com.fingard.xuesl.netty.share.eventflow.inbound.InBoundHandlerA;
import com.fingard.xuesl.netty.share.eventflow.inbound.InBoundHandlerB;
import com.fingard.xuesl.netty.share.eventflow.inbound.InBoundHandlerC;
import com.fingard.xuesl.netty.share.eventflow.outbound.OutBoundHandlerA;
import com.fingard.xuesl.netty.share.eventflow.outbound.OutBoundHandlerB;
import com.fingard.xuesl.netty.share.eventflow.outbound.OutBoundHandlerC;
import com.fingard.xuesl.netty.share.lifecycle.LifeCyCleTestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Slf4j
public class Server {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        ((NioEventLoopGroup) bossGroup).setIoRatio(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new InBoundHandlerA());
                        socketChannel.pipeline().addLast(new InBoundHandlerB());
                        socketChannel.pipeline().addLast(new InBoundHandlerC());

                        socketChannel.pipeline().addLast(new OutBoundHandlerA());
                        socketChannel.pipeline().addLast(new OutBoundHandlerB());
                        socketChannel.pipeline().addLast(new OutBoundHandlerC());
                    }
                });
        bind(serverBootstrap, 8080);
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
