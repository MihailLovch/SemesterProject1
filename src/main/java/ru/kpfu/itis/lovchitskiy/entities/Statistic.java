package ru.kpfu.itis.lovchitskiy.entities;

import java.util.Objects;

public class Statistic {
    private Integer calorie;
    private Integer fat;
    private Integer carb;
    private Integer proteins;

    public Statistic(Integer calorie, Integer fat, Integer carb, Integer proteins) {
        this.calorie = calorie;
        this.fat = fat;
        this.carb = carb;
        this.proteins = proteins;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public void setCarb(Integer carb) {
        this.carb = carb;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public Integer getFat() {
        return fat;
    }

    public Integer getCarb() {
        return carb;
    }

    public Integer getProteins() {
        return proteins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(calorie, statistic.calorie) && Objects.equals(fat, statistic.fat) && Objects.equals(carb, statistic.carb) && Objects.equals(proteins, statistic.proteins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calorie, fat, carb, proteins);
    }
}
