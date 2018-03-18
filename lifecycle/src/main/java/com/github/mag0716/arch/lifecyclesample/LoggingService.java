package com.github.mag0716.arch.lifecyclesample;

import android.arch.lifecycle.LifecycleService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import hugo.weaving.DebugLog;

/**
 * Created by mag0716 on 2018/03/17.
 */
public class LoggingService extends LifecycleService {

    private final static String TAG = LoggingService.class.getSimpleName();

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate : " + getLifecycle().getCurrentState());
    }

    @DebugLog
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand : " + getLifecycle().getCurrentState());
        return super.onStartCommand(intent, flags, startId);
    }

    @DebugLog
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind : " + getLifecycle().getCurrentState());
        return super.onBind(intent);
    }

    @DebugLog
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy : " + getLifecycle().getCurrentState());
    }

}
