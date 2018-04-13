package com.github.mag0716.common;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import hugo.weaving.DebugLog;

/**
 * Created by mag0716 on 2018/03/17.
 */

public class LoggingObserver implements LifecycleObserver {

    @DebugLog
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner owner) {
    }

    @DebugLog
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(LifecycleOwner owner) {
    }

    @DebugLog
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(LifecycleOwner owner) {
    }

    @DebugLog
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(LifecycleOwner owner) {
    }

    @DebugLog
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(LifecycleOwner owner) {
    }

    @DebugLog
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
    }

    @DebugLog
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner owner, Lifecycle.Event event) {
    }

}
