package com.spirit.community.rtc.session;

import io.netty.channel.ChannelHandlerContext;

public class session {
    private static final session _instance = new session();
    private session(){}
    public static session getInstance(){
        return _instance;
    }
    private ChannelHandlerContext ctx;

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
}
