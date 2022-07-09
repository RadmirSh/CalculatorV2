package com.example.calculatorv2.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.calculatorv2.model.Calculator;
import com.example.calculatorv2.model.Operator;

import java.io.Serializable;
import java.text.DecimalFormat;

public class CalculatorPresenter implements Serializable {

    private final DecimalFormat formater = new DecimalFormat("#.##");

    private CalculatorView view;
    private Calculator calculator;

    private double argOne;

    private Double argTwo;

    private Operator selectedOperator;

    private double lastResult;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void setLastResult(Double lastResult) {
        this.lastResult = lastResult;
    }

    public Double getLastResult() {
        return lastResult;
    }


    public void onDigitPressed(int digit) {

        if (argTwo == null) {

            argOne = argOne * 10 + digit;

            showFormatted(argOne);
            lastResult = argOne;
        } else {
            argTwo = argTwo * 10 + digit;

            showFormatted(argTwo);
            lastResult = argTwo;

        }
    }

    public void onOperatorPressed(Operator operator) {
        if (selectedOperator != null && selectedOperator != Operator.EQUAL) {

            argOne = calculator.perform(argOne, argTwo, selectedOperator);

            showFormatted(argOne);
            lastResult = argOne;
        }
        if (selectedOperator == Operator.EQUAL) {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
            lastResult = argOne;
        }

        argTwo = 0.0;
        lastResult = argOne;

        selectedOperator = operator;
    }

    public void onDotPressed() {
        if (argTwo == null) {
            argOne = argOne * 0.1;
            showFormatted(argOne);
        } else {
            argTwo = argTwo * 0.1;
            showFormatted(argTwo);
        }
    }

    private void showFormatted(double value) {
        view.showResult(formater.format(value));
    }

}
