package com.github.mag0716.arch.livedatasample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.Date;

public class ClockViewModel extends AndroidViewModel {

    private MutableLiveData<Date> clock;

    public ClockViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Date> getClock() {
        if (clock == null) {
            clock = new ClockLiveData();
        }
        return clock;
    }
}
