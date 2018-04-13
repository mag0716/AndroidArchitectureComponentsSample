package com.github.mag0716.arch.lifecyclesample;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.mag0716.common.LoggingObserver;

import hugo.weaving.DebugLog;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private LifecycleObserver lifecycleObserver = new LoggingObserver();
    private Intent serviceIntent;

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate : " + getLifecycle().getCurrentState());

        Observable.just(1000)
                .observeOn(Schedulers.io())
                .subscribe(delay -> {
                    Thread.sleep(delay);
                    Log.d(TAG, "delayed onCreate : " + getLifecycle().getCurrentState());
                });

        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(lifecycleObserver);
    }

    @DebugLog
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onCreate : " + getLifecycle().getCurrentState());
    }

    @DebugLog
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart : " + getLifecycle().getCurrentState());
    }

    @DebugLog
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume : " + getLifecycle().getCurrentState());

        serviceIntent = new Intent(this, LoggingService.class);
        startService(serviceIntent);
    }

    @DebugLog
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState : " + getLifecycle().getCurrentState());
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState : " + getLifecycle().getCurrentState());
    }

    @DebugLog
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause : " + getLifecycle().getCurrentState());
        stopService(serviceIntent);
    }

    @DebugLog
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop : " + getLifecycle().getCurrentState());
    }

    @DebugLog
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy : " + getLifecycle().getCurrentState());
        getLifecycle().removeObserver(lifecycleObserver);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
