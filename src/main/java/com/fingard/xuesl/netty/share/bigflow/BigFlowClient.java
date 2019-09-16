package com.fingard.xuesl.netty.share.bigflow;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;

/**
 * @author xuesl
 * @date 2018/12/13
 */
public class BigFlowClient {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup worker = new NioEventLoopGroup();

        bootstrap
                .group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ByteBuf byteBuf = ctx.alloc().buffer();
                                byteBuf.writeBytes("10000000".getBytes("GBK"));
                                for (int i = 0; i < 10000000; i++) {
                                    byteBuf.writeBytes("a".getBytes("GBK"));
                                }
//                                System.out.println("可读长度:" + byteBuf.readableBytes() -4);

                                ctx.channel().writeAndFlush(byteBuf);
//                                ctx.close();
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
