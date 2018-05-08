package com.cantacoop.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int sum = 0;
    private TextView tvResult;
    private Button btnOk;
    private EditText editTextSecond;

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

        editTextSecond = findViewById(R.id.editTextSecond);

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultString = editTextSecond.getText().toString();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", resultString);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
