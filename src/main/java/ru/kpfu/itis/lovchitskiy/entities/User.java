package ru.kpfu.itis.lovchitskiy.entities;


import ru.kpfu.itis.lovchitskiy.utils.MyEncoder;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String password;
    private Boolean sex;
    private Float weight;
    private Float height;
    private Integer age;
    private Integer id;
    private String dateOfBirth;
    private Integer consumedCalories = 0;
    private Integer consumedCarbs = 0;
    private Integer consumedFats = 0;
    private Integer consumedProteins = 0;
    public static final String NAME_KEY = "name";
    public static final String PASSWORD_KEY = "password";
    public static final String EMAIL_KEY = "email";
    public static final String WEIGHT_KEY = "weight";
    public static final String HEIGHT_KEY = "height";
    public static final String AGE_KEY = "age";
    public static final String SEX_KEY = "sex";
    public static final String USER = "user";

    public User(Integer id,String name, String email, String password, Boolean sex, float weight, float height, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = MyEncoder.encryptPassword(password);
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String name, String email, String password, Boolean sex, float weight, float height, String dateOfBirth) {
        this(-1, name, email, password, sex, weight, height, dateOfBirth);
    }
    public User(String email, String password) {
        this.email = email;
        this.password = MyEncoder.encryptPassword(password);
    }

    public User(HttpServletRequest req) {
        this.name = req.getParameter(NAME_KEY);
        this.email = req.getParameter(EMAIL_KEY);
        this.password = MyEncoder.encryptPassword(req.getParameter(PASSWORD_KEY));
        this.weight = Float.parseFloat(req.getParameter(WEIGHT_KEY));
        this.height = Float.parseFloat(req.getParameter(HEIGHT_KEY));
        this.dateOfBirth = req.getParameter(AGE_KEY);
        this.sex = Boolean.valueOf(req.getParameter(SEX_KEY));
    }

    public double getCalories() {
        double calories = 10 * weight + 6.25 * height - 5 * age;
        if (sex) {
            return 1.2 * (calories + 5);
        } else {
            return 1.2 * (calories - 161);
        }
    }
    public Timestamp getDateOfBirthAsTimestamp(){
        String valueFromHtml = dateOfBirth;
        String dateTimeString = valueFromHtml.replace("T", " ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return Timestamp.valueOf(dateTime);
    }
    public double getFats() {
        return 0.8 * weight;
    }

    // белки
    public double getProteins() {
        return 1.5 * weight;
    }

    // углеводы
    public double getCarbs() {
        return 2 * weight;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getSex() {
        return sex;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = MyEncoder.encryptPassword(password);
    }

    public Integer getConsumedCalories() {
        return consumedCalories;
    }

    public Integer getConsumedCarbs() {
        return consumedCarbs;
    }

    public Integer getConsumedFats() {
        return consumedFats;
    }

    public Integer getConsumedProteins() {
        return consumedProteins;
    }

    public void eatFood(Recipe recipe, int grams) {
        consumedCalories += recipe.getCalories() * grams/100;
        consumedCarbs += recipe.getCarbohydrates()* grams/100;
        consumedFats += recipe.getFats()* grams/100;
        consumedProteins += recipe.getProteins() * grams/100;
    }

    public void setStatistic(Statistic statistic) {
        consumedCalories = statistic.getCalorie();
        consumedCarbs = statistic.getCarb();
        consumedFats = statistic.getFat();
        consumedProteins = statistic.getProteins();
    }

    public Statistic getStatistic() {
        return new Statistic(
                consumedCalories,
                consumedFats,
                consumedCarbs,
                consumedProteins
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(sex, user.sex) && Objects.equals(weight, user.weight) && Objects.equals(height, user.height) && Objects.equals(age, user.age) && Objects.equals(id, user.id)  && Objects.equals(consumedCalories, user.consumedCalories) && Objects.equals(consumedCarbs, user.consumedCarbs) && Objects.equals(consumedFats, user.consumedFats) && Objects.equals(consumedProteins, user.consumedProteins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, sex, weight, height, age, id, consumedCalories, consumedCarbs, consumedFats, consumedProteins);
    }
}
