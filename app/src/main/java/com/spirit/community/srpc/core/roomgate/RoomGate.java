package com.spirit.community.srpc.core.roomgate;



import com.spirit.community.srpc.core.SRpcBizApp;
import com.spirit.community.srpc.core.State;

import com.spirit.tba.core.TbaEvent;

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

public class RoomGate {

    private Channel channel = null;
    private Long serverRandom = null;

    public RoomGate() {

    }

    public void open(String host, Integer port) throws Exception {

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
                        pipeline.addLast("decode", new RoomGateProtocolDecoder());
                        pipeline.addLast("encode", new RoomGateProtocolEncoder());
                        pipeline.addLast(new RoomGateRpcEventHandler());
                    }
                })
                .connect(new InetSocketAddress(host, port))
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        channel = (SocketChannel) future.channel();
                        SRpcBizApp.getInstance().setState(State.ROOMGATE_CONNECTING);
                    } else {
                        future.channel().close();
                        group.shutdownGracefully();
                        SRpcBizApp.getInstance().setState(State.ROOMGATE_DISCONNECT);
                    }
                });
    }

    public void close() {
        channel.close();
        SRpcBizApp.getInstance().setState(State.LOGIN_SERVER_DISCONNECT);
    }

    public Channel getChannel() {
        return channel;
    }

    public void putEvent(TbaEvent ev) {
        channel.writeAndFlush(ev);
    }

    public Long getServerRandom() {
        return serverRandom;
    }

    public void setServerRandom(Long serverRandom) {
        this.serverRandom = serverRandom;
    }
}
