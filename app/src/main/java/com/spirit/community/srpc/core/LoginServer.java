package com.spirit.community.srpc.core;

import com.spirit.tba.core.TsEvent;
import java.net.InetSocketAddress;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class LoginServer {

    private Channel channel = null;

    public LoginServer() {

    }

    public void connect(String host, Integer port) throws Exception {

        NioEventLoopGroup group = new NioEventLoopGroup();
        new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new IdleStateHandler(0, 30, 0));
                        pipeline.addLast("decode", new TbaProtocolDecoder());
                        pipeline.addLast("encode", new TbaProtocolEncoder());
                        pipeline.addLast(new RpcEventHandler());
                    }
                })
                .connect(new InetSocketAddress(host, port))
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        channel = (SocketChannel) future.channel();
                        SRpcClient.getInstance().setState(State.LOGIN_SERVER_CONNECT);
                    } else {
                        future.channel().close();
                        group.shutdownGracefully();
                        SRpcClient.getInstance().setState(State.LOGIN_SERVER_DISCONNECT);
                    }
                });
    }

    public void close() {
        channel.close();
        SRpcClient.getInstance().setState(State.LOGIN_SERVER_DISCONNECT);
    }

    public Channel getChannel() {
        return channel;
    }

    public void putEvent(TsEvent ev) {
        channel.writeAndFlush(ev);
    }
}
