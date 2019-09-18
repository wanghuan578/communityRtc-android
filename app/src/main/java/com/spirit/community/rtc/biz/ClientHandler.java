package com.spirit.community.rtc.biz;



import com.alibaba.fastjson.JSON;
import com.spirit.community.rtc.login.protocol.rpc.thrift.ClientLoginRes;
import com.spirit.community.rtc.login.protocol.rpc.thrift.ClientPasswordLoginReq;
import com.spirit.community.rtc.login.protocol.rpc.thrift.HelloNotify;
import com.spirit.community.rtc.session.session;
import com.spirit.tba.core.TsEvent;
import com.spirit.tba.core.TsRpcHead;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


@Sharable
public class ClientHandler extends SimpleChannelInboundHandler{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		session.getInstance().setCtx(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
    	
    	if (msg instanceof HelloNotify) {

    		HelloNotify notify = (HelloNotify) msg;
    		System.out.println(JSON.toJSONString(notify, true));

    		ClientPasswordLoginReq req = new ClientPasswordLoginReq();
    		req.setCheck_sum("112211221121122122trfgft4rfrtfghtrgy");
    		req.setClient_mac("112233445566");
    		req.setClient_version("1.0.0.0");

			TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_PASSWORD_LOGIN_REQ);
			ctx.write(new TsEvent(head, req, 1024));
			ctx.flush();
    	}
    	else if (msg instanceof ClientLoginRes) {


    	}

    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub

	}


}
