package com.github.mag0716.arch.lifecyclesample;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new TestObserver());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    static class TestObserver implements LifecycleObserver {

        @DebugLog
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate() {
        }

        @DebugLog
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart() {
        }

        @DebugLog
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume() {
        }

        @DebugLog
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause() {
        }

        @DebugLog
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop() {
        }

        @DebugLog
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy() {
        }
    }
}
