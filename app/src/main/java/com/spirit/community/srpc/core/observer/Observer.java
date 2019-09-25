package com.spirit.community.srpc.core.observer;

public class Observer {
    public interface EventListener {
        void onEvent(int type, Object msg);
    }
}

