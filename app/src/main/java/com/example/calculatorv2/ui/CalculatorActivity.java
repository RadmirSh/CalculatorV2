package com.example.calculatorv2.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        operators.put(R.id.equal, Operator.EQUAL);

        /*digitClickListener для обработки нажатия кнопок и передачи presenter'у*/
        View.OnClickListener operatorsOnClickListener = view -> presenter.onOperatorPressed(operators.get(view.getId()));

        /*выставление для кнопок listener */
        findViewById(R.id.key_addition).setOnClickListener(operatorsOnClickListener);
        findViewById(R.id.key_subtraction).setOnClickListener(operatorsOnClickListener);
        findViewById(R.id.composition).setOnClickListener(operatorsOnClickListener);
        findViewById(R.id.division).setOnClickListener(operatorsOnClickListener);

        /*digitClickListener для обработки нажатия кнопки "." и передачи presenter'у*/
        findViewById(R.id.dot).setOnClickListener(view -> presenter.onDotPressed());

        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();

                    Theme selectedTheme = (Theme) intent.getSerializableExtra(SelectThemeActivity.EXTRA_THEME);

                    themeRepository.saveTheme(selectedTheme);

                    recreate();
                }
            }
        });

        findViewById(R.id.theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME, themeRepository.getSavedTheme());

                themeLauncher.launch(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("lastResult", presenter.getLastResult());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        presenter.setLastResult((double) savedInstanceState.getSerializable("lastResult"));
        showResult(String.valueOf(presenter.getLastResult()));
        Log.d("calcCreate", "reCreate");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}

