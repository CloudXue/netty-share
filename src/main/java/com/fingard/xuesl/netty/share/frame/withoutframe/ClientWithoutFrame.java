package com.fingard.xuesl.netty.share.frame.withoutframe;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;

/**
 * @author xuesl
 * @date 2018/11/6
 */
public class ClientWithoutFrame {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup worker = new NioEventLoopGroup();

        bootstrap
                .group(worker)
                .channel(NioSocketChannel.class)
                //TODO 演示打开关闭TCP_NODELAY
//                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                for (int i = 0; i < 100; i++) {
                                    ByteBuf byteBuf = ctx.alloc().buffer();
                                    byteBuf.writeBytes("这是一条测试拆包粘包的测试数据，我得让消息再长点才好产生现象。".getBytes(Charset.forName("UTF-8")));
                                    ctx.channel().writeAndFlush(byteBuf);
                                }
                                ctx.close();
                            }
                        });
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 10001).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
