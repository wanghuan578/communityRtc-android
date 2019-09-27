package com.spirit.community.srpc.core;

import com.spirit.community.common.RpcEventType;
import com.spirit.tba.core.TbaEvent;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class RpcEventHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SRpcClient.getInstance().notify(RpcEventType.NETWORK_DISCONNECT, null);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        TbaEvent ev = (TbaEvent) msg;
		SRpcClient.getInstance().notify(ev.getHead().GetType(), ev.getBody());
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub

	}


}
