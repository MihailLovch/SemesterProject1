package ru.kpfu.itis.lovchitskiy.utils;

public enum AttributeName {
    DBExecutor("DBExecutor"),
    Notice("notice"),
    Error("error"),
    RecipeRepository("RecipeRepository"),
    WorkoutRepository("WorkoutRepository"),
    StatisticRepository("StatisticRepository");

    public final String value;

    private AttributeName(String value) {
        this.value = value;
    }
}
