package com.example.calculatorv2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculatorv2.R;
import com.example.calculatorv2.model.CalculatorImpl;
import com.example.calculatorv2.model.Operator;
import com.example.calculatorv2.model.Theme;
import com.example.calculatorv2.model.ThemeRepository;
import com.example.calculatorv2.model.ThemeRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {
    /*отображение, сбор информации*/

    private TextView resultTxt;

    private CalculatorPresenter presenter;

    private ThemeRepository themeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeRepository = ThemeRepositoryImpl.getInstance(this);
        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_calculator);

        resultTxt = findViewById(R.id.result);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        /*MAP чтобы вытащить значения*/
        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);

        /*digitClickListener для обработки нажатия кнопок и передачи presenter'у*/
        View.OnClickListener digitOnClickListener = view -> presenter.onDigitPressed(digits.get(view.getId()));

        /*выставление для кнопок listener */
        findViewById(R.id.key_1).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitOnClickListener);

        /*MAP чтобы вытащить значения операторов*/
        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_addition, Operator.ADD);
        operators.put(R.id.key_subtraction, Operator.SUB);
        operators.put(R.id.composition, Operator.COMP);
        operators.put(R.id.division, Operator.DIV);

        /*digitClickListener для обработки нажатия кнопок и передачи presenter'у*/
        View.OnClickListener operatorsOnClickListener = view -> presenter.onOperatorPressed(operators.get(view.getId()));

        /*выставление для кнопок listener */
        findViewById(R.id.key_addition).setOnClickListener(operatorsOnClickListener);
        findViewById(R.id.key_subtraction).setOnClickListener(operatorsOnClickListener);
        findViewById(R.id.composition).setOnClickListener(operatorsOnClickListener);
        findViewById(R.id.division).setOnClickListener(operatorsOnClickListener);

        /*digitClickListener для обработки нажатия кнопки "." и передачи presenter'у*/
        findViewById(R.id.dot).setOnClickListener(view -> presenter.onDotPressed());

        Button themeDefault = findViewById(R.id.theme1);
        Button themeWhite = findViewById(R.id.theme2);
        Button themeBlack = findViewById(R.id.theme3);

        if (themeDefault != null) {
            themeDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    themeRepository.saveTheme(Theme.DEFAULT);
                    recreate();
                }
            });
        }
        if (themeWhite != null) {
            themeWhite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    themeRepository.saveTheme(Theme.WHITE);
                    recreate();
                }
            });
        }
        if (themeBlack != null) {
            themeBlack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    themeRepository.saveTheme(Theme.BLACK);
                    recreate();
                }
            });
        }
    }
    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}

