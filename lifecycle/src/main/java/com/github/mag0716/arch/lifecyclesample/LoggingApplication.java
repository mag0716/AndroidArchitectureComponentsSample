package com.github.mag0716.arch.lifecyclesample;

import android.app.Application;
import android.arch.lifecycle.ProcessLifecycleOwner;

import com.github.mag0716.common.LoggingObserver;

import hugo.weaving.DebugLog;

/**
 * Created by mag0716 on 2018/03/17.
 */

public class LoggingApplication extends Application {

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();

        // 最初に遷移する Activity のイベントに従って通知される
        ProcessLifecycleOwner.get()
                .getLifecycle()
                .addObserver(new LoggingObserver());
    }
}
