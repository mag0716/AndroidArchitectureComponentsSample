package com.github.mag0716.arch.livedatasample;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    private TextView resultText, mapResultText, switchMapResultText;
    private EditText valueEditText, mapValueEditText, switchMapValueEditText;

    private MutableLiveData<String> valueLiveData = new MutableLiveData<>();

    private MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private LiveData<String> userNameLiveData = Transformations.map(userLiveData, user -> user.name);

    private MutableLiveData<String> userIdLiveData = new MutableLiveData<>();
    private LiveData<User> lazyUserLiveData = Transformations.switchMap(userIdLiveData, id -> {
        MutableLiveData<User> liveData = new MutableLiveData<>();
        liveData.setValue(createUser(id));
        return liveData;
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initViewForMap();
        initViewForSwitchMap();

        valueLiveData.observe(this, changedValue -> {
            resultText.setText(changedValue);
            Toast.makeText(MainActivity.this, "onChanged : " + changedValue, Toast.LENGTH_SHORT).show();
        });
        userNameLiveData.observe(this, changedValue -> {
            mapResultText.setText(changedValue);
            Toast.makeText(MainActivity.this, "onChanged : " + changedValue, Toast.LENGTH_SHORT).show();
        });
        lazyUserLiveData.observe(this, changedValue -> {
            switchMapResultText.setText(changedValue.toString());
            Toast.makeText(MainActivity.this, "onChanged : " + changedValue.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    private class User {
        final String id;
        final String name;

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("User(%s, %s)", id, name);
        }
    }

    private User createUser(@NonNull String id) {
        return new User(id, String.format("name%s", id));
    }

    private void initView() {
        resultText = (TextView) findViewById(R.id.result_text);
        valueEditText = (EditText) findViewById(R.id.value_edit_text);
        findViewById(R.id.set_value_button).setOnClickListener(view -> {
            valueLiveData.setValue(valueEditText.getText().toString());
        });
        findViewById(R.id.post_value_button).setOnClickListener(view -> {
            Single.just(valueEditText.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .delay(5, TimeUnit.SECONDS)
                    .subscribe(value -> valueLiveData.postValue(value));
        });
    }

    private void initViewForMap() {
        mapResultText = (TextView) findViewById(R.id.map_result_text);
        mapValueEditText = (EditText) findViewById(R.id.map_value_edit_text);
        findViewById(R.id.map_set_value_button).setOnClickListener(view -> {
            final String id = mapValueEditText.getText().toString();
            userLiveData.setValue(createUser(id));
        });
    }

    private void initViewForSwitchMap() {
        switchMapResultText = (TextView) findViewById(R.id.switch_map_result_text);
        switchMapValueEditText = (EditText) findViewById(R.id.switch_map_value_edit_text);
        findViewById(R.id.switch_map_set_value_button).setOnClickListener(view -> {
            userIdLiveData.setValue(switchMapValueEditText.getText().toString());
        });
    }
}
