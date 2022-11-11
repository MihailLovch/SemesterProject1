package ru.kpfu.itis.lovchitskiy.entities;

import java.util.Objects;

public class Recipe {
    private int id;
    private String name;
    private int calories;
    private int proteins;
    private int fats;
    private int carbohydrates;
    public Recipe(int id, String name, int calories, int proteins, int fats, int carbohydrates) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
    public Recipe(String name, int calories, int proteins, int fats, int carbohydrates) {
        this(-1,name,calories,proteins,fats,carbohydrates);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }


    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getId() {
        return id;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return calories == recipe.calories && id == recipe.id && proteins == recipe.proteins && fats == recipe.fats && carbohydrates == recipe.carbohydrates && Objects.equals(name, recipe.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, id, proteins, fats, carbohydrates);
    }
}
