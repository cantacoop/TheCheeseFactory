package com.cantacoop.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private TextView tvResult;
    private Button btnCalculate;

    private RadioGroup rgOperator;

    CustomViewGroup viewGroup1;
    CustomViewGroup viewGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)) {
            // Fix screen orientation to Portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.activity_main);

        initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x; // Screen Width
        int height = size.y; // Screen Height
//        Toast.makeText(MainActivity.this,
//                "Width " + width + " Height " + height,
//                Toast.LENGTH_LONG)
//                .show();


    }

    private void initInstances() {
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        rgOperator = findViewById(R.id.rgOperator);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val1 = 0;
                int val2 = 0;
                int sum = 0;

                try {
                    val1 = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException e) {
                }

                try {
                    val2 = Integer.parseInt(editText2.getText().toString());
                } catch (NumberFormatException e) {
                }

                // Check Operator
                switch (rgOperator.getCheckedRadioButtonId()) {
                    case R.id.rbPlus:
                        sum = val1 + val2;
                        break;
                    case R.id.rbMinus:
                        sum = val1 - val2;
                        break;
                    case R.id.rbMultiply:
                        sum = val1 * val2;
                        break;
                    case R.id.rbDivide:
                        sum = val1 / val2;
                        break;
                }

                tvResult.setText("= " + sum);

                // Show Result
                Log.d("Calculation", "Result = " + sum);

                Toast.makeText(MainActivity.this,
                        "Result = " + sum,
                        Toast.LENGTH_SHORT)
                        .show();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("result", sum);

                // Playground
                Coordinate c1 = new Coordinate();
                c1.x = 5;
                c1.y = 10;
                c1.z = 20;
                Bundle bundle = new Bundle();
                bundle.putInt("x", c1.x);
                bundle.putInt("y", c1.y);
                bundle.putInt("z", c1.z);
                intent.putExtra("cBundle", bundle);

                // Serializable Lab
                CoordinateSerializable c2 = new CoordinateSerializable();
                c2.x = 5;
                c2.y = 10;
                c2.z = 20;
                intent.putExtra("cSerializable", c2);

                CoordinateParcelable c3 = new CoordinateParcelable();
                c3.x = 5;
                c3.y = 10;
                c3.z = 20;
                intent.putExtra("cParcelable", c3);

                startActivityForResult(intent, 12345);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        viewGroup1 = findViewById(R.id.viewGroup1);
        viewGroup2 = findViewById(R.id.viewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if it is a result from SecondActivity
        if (requestCode == 12345) {
            if (resultCode == RESULT_OK) {
                // Get data from data's extra
                String result = data.getStringExtra("result");
                Toast.makeText(MainActivity.this,
                        result,
                        Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
