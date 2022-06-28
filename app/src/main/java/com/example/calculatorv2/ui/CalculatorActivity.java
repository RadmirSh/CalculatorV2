package com.example.calculatorv2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.calculatorv2.R;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {
    /*отображение, сбор информации*/

    private TextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultTxt = findViewById(R.id.result);
    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}