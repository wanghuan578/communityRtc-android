package com.spirit.community.srpc.core.observer;

public interface EventListener {
    void onEvent(int type, Object msg);
}
