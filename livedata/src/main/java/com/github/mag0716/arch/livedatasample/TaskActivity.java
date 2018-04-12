package com.github.mag0716.arch.livedatasample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mag0716.common.LoggingObserver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskActivity extends AppCompatActivity implements Observer<Date> {

    private final LoggingObserver loggingObserver = new LoggingObserver();
    //private final ClockLiveData clockLiveData = new ClockLiveData();
    private ClockViewModel clockViewModel;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        clockViewModel = ViewModelProviders.of(this).get(ClockViewModel.class);
        text = findViewById(R.id.text);


        getLifecycle().addObserver(loggingObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //clockLiveData.observe(this, this);
        clockViewModel.getClock().observe(this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //clockLiveData.removeObserver(this);
        clockViewModel.getClock().removeObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(loggingObserver);
    }

    @Override
    public void onChanged(@Nullable Date date) {
        updateText(date);
    }

    private void updateText(@NonNull Date date) {
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        text.setText(format.format(date));
    }
}
