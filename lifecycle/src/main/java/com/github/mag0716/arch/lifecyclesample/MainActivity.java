package com.github.mag0716.arch.lifecyclesample;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    private TestObserver lifecycleObserver = new TestObserver();

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(lifecycleObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(lifecycleObserver);
    }

    static class TestObserver implements LifecycleObserver {

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
    }
}
