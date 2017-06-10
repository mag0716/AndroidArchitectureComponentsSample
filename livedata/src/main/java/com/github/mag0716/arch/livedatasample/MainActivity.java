package com.github.mag0716.arch.livedatasample;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    private TextView resultText;
    private EditText valueEditText;

    private MutableLiveData<String> valueLiveData = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById(R.id.result_text);
        valueEditText = (EditText) findViewById(R.id.value_edit_text);
        findViewById(R.id.set_value_button).setOnClickListener(view -> {
            valueLiveData.setValue(valueEditText.getText().toString());
        });

        valueLiveData.observe(this, changedValue -> {
            resultText.setText(changedValue);
            Toast.makeText(MainActivity.this, "onChanged : " + changedValue, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
