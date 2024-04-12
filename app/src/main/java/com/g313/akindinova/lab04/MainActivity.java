package com.g313.akindinova.lab04;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox[] chBox = new CheckBox[4];
    EditText[] counts = new EditText[4];
    EditText[] prices = new EditText[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chBox[0] = findViewById(R.id.apple);
        chBox[1] = findViewById(R.id.strawberry);
        chBox[2] = findViewById(R.id.blueberry);
        chBox[3] = findViewById(R.id.potatoes);

        counts[0] = findViewById(R.id.apl_count);
        counts[1] = findViewById(R.id.strb_count);
        counts[2] = findViewById(R.id.blb_count);
        counts[3] = findViewById(R.id.potato_count);

        prices[0] = findViewById(R.id.apl_price);
        prices[1] = findViewById(R.id.strb_price);
        prices[2] = findViewById(R.id.blb_price);
        prices[3] = findViewById(R.id.potato_price);
    }

    @SuppressLint("DefaultLocale")
    public void onCalculate(View view) {
        StringBuilder builder = new StringBuilder();
        double result = 0;
        for (int i = 0; i < chBox.length; i++) {
            if (!chBox[i].isChecked()) continue;

            try {
                double value = Double.parseDouble(counts[i].getText().toString());
                double price = Double.parseDouble(prices[i].getText().toString());

                if(value <= 0 || price <= 0) {
                    Toast.makeText(getApplicationContext(), "Price or Count not can be less than 1. Skipped", Toast.LENGTH_LONG).show();
                    continue;
                }

                double tempRes = value * price;
                result += tempRes;

                builder.
                        append(String.format("%d: %.2f * %s = %.2f * %.2f = %.2f\n", i, value, chBox[i].getText().toString(), value, price, tempRes));
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), String.format("Field %s incorrect. Skipped", chBox[i].getText()), Toast.LENGTH_LONG).show();
            }
        }
        builder.append(String.format("total - %.2f", result));
        Toast.makeText(getApplicationContext(), String.format("total - %.2f", result), Toast.LENGTH_LONG).show();

        ((TextView)findViewById(R.id.result)).setText(builder.toString());
    }
}