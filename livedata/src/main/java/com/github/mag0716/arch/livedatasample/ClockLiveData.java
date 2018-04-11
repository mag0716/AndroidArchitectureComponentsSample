package com.github.mag0716.arch.livedatasample;

import android.arch.lifecycle.LiveData;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ClockLiveData extends LiveData<Date> {

    private Disposable disposable;

    @Override
    protected void onActive() {
        super.onActive();
        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(interval -> postValue(new Date(System.currentTimeMillis())));
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
