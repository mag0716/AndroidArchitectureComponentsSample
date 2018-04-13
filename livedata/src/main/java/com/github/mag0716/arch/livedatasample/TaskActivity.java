package com.github.mag0716.arch.livedatasample;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mag0716.arch.livedatasample.databinding.ActivityTaskBinding;
import com.github.mag0716.common.LoggingObserver;

public class TaskActivity extends AppCompatActivity {

    private final LoggingObserver loggingObserver = new LoggingObserver();

    private ActivityTaskBinding binding;
    //private final ClockLiveData clockLiveData = new ClockLiveData();
    private ClockViewModel clockViewModel;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task);
        text = findViewById(R.id.text);
        clockViewModel = ViewModelProviders.of(this).get(ClockViewModel.class);

        binding.setLifecycleOwner(this); // Sets the LifecycleOwner that should be used for observing changes of LiveData in this binding.
        binding.setViewmodel(clockViewModel);

        getLifecycle().addObserver(loggingObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //clockLiveData.observe(this, this);
        //clockViewModel.getClock().observe(this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //clockLiveData.removeObserver(this);
        //clockViewModel.getClock().removeObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(loggingObserver);
    }

//    @Override
//    public void onChanged(@Nullable Date date) {
//        if (date != null) {
//            updateText(date);
//        }
//    }
//
//    private void updateText(@NonNull Date date) {
//        final SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
//        text.setText(format.format(date));
//    }
}
