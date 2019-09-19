package com.spirit.community.srpc.core;

import com.spirit.community.srpc.core.observer.EventObserver;
import com.spirit.tba.core.TsEvent;
import com.spirit.tba.core.TsRpcHead;


import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


public class SRpcClient {

    private String host = null;
    private Integer port = null;
    private Channel channel = null;
    private EventObserver observer = null;

    private static SRpcClient _instance = null;

    public static SRpcClient getInstance(){
        synchronized (SRpcClient.class) {
            if (_instance == null) {
                _instance = new SRpcClient();
            }
        }
        return _instance;
    }

    public void init() {

    }

    private SRpcClient(){

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
                }
                else {
                    //Log.e(TAG, "connect failed");
                    future.channel().close();
                    group.shutdownGracefully();
                }
            });
    }
 
    public Channel getChannel() {
        return channel;
    }

    public void register(EventObserver observer) {
        this.observer = observer;
    }

    public void notify(int type, Object obj) {
        if (observer != null) {
            observer.onEvent(type, obj);
        }
    }

    public void putEvent(TsEvent ev) {
        channel.writeAndFlush(ev);
    }

}
