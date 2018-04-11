package com.github.mag0716.arch.livedatasample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mag0716.common.LoggingObserver;

public class TaskActivity extends AppCompatActivity {

    private final LoggingObserver loggingObserver = new LoggingObserver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        getLifecycle().addObserver(loggingObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(loggingObserver);
    }
}
