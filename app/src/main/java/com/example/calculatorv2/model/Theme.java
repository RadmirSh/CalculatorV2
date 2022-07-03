package com.example.calculatorv2.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.example.calculatorv2.R;

public enum Theme {
    DEFAULT(R.style.Theme_CalculatorV2, R.string.theme_1, "themedefault"),
    WHITE(R.style.Theme_CalculatorV2_White, R.string.theme_2, "themewhite"),
    BLACK(R.style.Theme_CalculatorV2_Black, R.string.theme_3, "themeblack");

    @StyleRes
    private int themeRes;
    @StringRes
    private int title;

    private String key;

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
