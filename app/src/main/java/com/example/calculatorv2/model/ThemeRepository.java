package com.example.calculatorv2.model;

import java.util.List;

public interface ThemeRepository {

    Theme getSavedTheme(); //текущая сохраненная тема

    void saveTheme(Theme theme); // сохранение темы

    List<Theme> getAll(); // возвращение списка всех доступных тем
}
