package com.github.mag0716.arch.livedatasample;

import android.arch.lifecycle.MutableLiveData;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import hugo.weaving.DebugLog;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ClockLiveData extends MutableLiveData<Date> {

    private Disposable disposable;

    @DebugLog
    @Override
    protected void onActive() {
        super.onActive();
        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(interval -> postValue(new Date(System.currentTimeMillis())));
    }

    @DebugLog
    @Override
    protected void onInactive() {
        super.onInactive();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
