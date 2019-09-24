package com.fingard.xuesl.netty.share.heartbeat.client;

import com.fingard.xuesl.netty.share.heartbeat.codec.PacketDecoder;
import com.fingard.xuesl.netty.share.heartbeat.codec.PacketEncoder;
import com.fingard.xuesl.netty.share.heartbeat.codec.PacketFilter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuesl
 * @date 2019/9/19
 */
@Slf4j
public class HeartBeatClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));

    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new ClientIdleStateHandler());
                            p.addLast(new PacketFilter());
                            p.addLast(new PacketEncoder());
                            p.addLast(new PacketDecoder());
                            p.addLast(new HeartBeatClientHander());
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isDone()) {
                        log.info("连接成功！");
                    }
                }
            });

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}
