package com.cantacoop.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int sum = 0;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);

        CoordinateSerializable c2 = (CoordinateSerializable) intent.getSerializableExtra("cSerializable");

        CoordinateParcelable c3 = (CoordinateParcelable) intent.getParcelableExtra("cParcelable");
        
        InitInstances();
    }

    private void InitInstances() {
        tvResult = findViewById(R.id.tvResult);
        tvResult.setText("Result = " + sum);
    }
}
