package com.spirit.community.srpc.core;

import com.spirit.community.srpc.core.loginserver.LoginServer;
import com.spirit.community.srpc.core.observer.Observer;
import com.spirit.community.srpc.core.roomgate.RoomGate;
import com.spirit.tba.core.TbaEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class SRpcBizApp {

    private Observer.EventListener listener = null;
    private AtomicInteger state = null;
    private LoginServer loginServer = null;
    private RoomGate roomGate = null;
    private Long userid;


    private static SRpcBizApp _instance = null;

    public static SRpcBizApp getInstance() {
        synchronized (SRpcBizApp.class) {
            if (_instance == null) {
                _instance = new SRpcBizApp();
            }
        }
        return _instance;
    }

    public void init() {
        state = new AtomicInteger();
        loginServer = new LoginServer();
        roomGate = new RoomGate();
    }

    private SRpcBizApp() {

    }

    public LoginServer getLoginServer() {
        return loginServer;
    }

    public RoomGate getRoomGate() {
        return roomGate;
    }

    public void register(Observer.EventListener listener) {
        this.listener = listener;
    }

    public void transfer2UI(int type, Object obj) {
        if (listener != null) {
            listener.onEvent(type, obj);
        }
    }

    public void loginServerConnect(String ip, int port) throws Exception {
        loginServer.connect(ip, port);
        state.getAndSet(State.LOGIN_SERVER_CONNECT);
    }

    public void loginServerClose() {
        loginServer.close();
        state.getAndSet(State.LOGIN_SERVER_DISCONNECT);
    }

    public void setState(int s) {
        state.getAndSet(s);
    }

    public int getState() {
        return state.get();
    }

    public void putEvent(TbaEvent ev) {

        switch (state.get()) {
            case State.LOGIN_SERVER_CONNECT:
            case State.LOGIN_SERVER_LOGIN: {
                loginServer.putEvent(ev);
            }
                break;

            case State.ROOMGATE_CONNECTING:
            case State.ROOMGATE_CONNECTED:
                roomGate.putEvent(ev);
                break;

            default:
                assert false;
                break;
        }

    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
