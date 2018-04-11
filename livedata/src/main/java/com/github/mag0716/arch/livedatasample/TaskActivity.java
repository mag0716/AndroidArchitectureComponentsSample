package com.github.mag0716.arch.livedatasample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mag0716.common.LoggingObserver;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TaskActivity extends AppCompatActivity {

    private final LoggingObserver loggingObserver = new LoggingObserver();
    private TextView text;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        text = findViewById(R.id.text);

        getLifecycle().addObserver(loggingObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(interval -> updateText());
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(loggingObserver);
    }

    private void updateText() {
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        text.setText(format.format(System.currentTimeMillis()));
    }
}
