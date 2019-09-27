package com.spirit.community.srpc.core;

import com.spirit.community.srpc.core.observer.Observer;
import com.spirit.tba.core.TbaEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class SRpcClient {

    private Observer.EventListener listener = null;
    private AtomicInteger state = null;
    private LoginServer loginServer = null;

    private static SRpcClient _instance = null;

    public static SRpcClient getInstance() {
        synchronized (SRpcClient.class) {
            if (_instance == null) {
                _instance = new SRpcClient();
            }
        }
        return _instance;
    }

    public void init() {
        state = new AtomicInteger();
        loginServer = new LoginServer();
    }

    private SRpcClient() {

    }

    public LoginServer getLoginServer() {
        return loginServer;
    }

    public void register(Observer.EventListener listener) {
        this.listener = listener;
    }

    public void notify(int type, Object obj) {
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
            case State.LOGIN_SERVER_LOGIN:
                {
                loginServer.putEvent(ev);
            }
                break;

            default:
                assert false;
                break;
        }

    }

}
