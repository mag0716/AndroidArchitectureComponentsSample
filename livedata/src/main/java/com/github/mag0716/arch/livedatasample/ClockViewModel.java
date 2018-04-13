package com.github.mag0716.arch.livedatasample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClockViewModel extends AndroidViewModel {

    private MutableLiveData<Date> clock = new ClockLiveData();

    public ClockViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Date> getClock() {
        return clock;
    }

    public LiveData<String> getClockText() {
        return Transformations.map(clock, clock -> {
            final SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return format.format(clock);
        });
    }
}
