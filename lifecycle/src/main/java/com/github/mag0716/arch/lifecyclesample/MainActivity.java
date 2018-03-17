package com.github.mag0716.arch.lifecyclesample;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    private LifecycleObserver lifecycleObserver = new LoggingObserver();
    private Intent serviceIntent;

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(lifecycleObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        serviceIntent = new Intent(this, LoggingService.class);
        startService(serviceIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(serviceIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(lifecycleObserver);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
