package com.spirit.community.srpc.core.observer;

public interface EventObserver {
    void onEvent(int type, Object msg);
}
