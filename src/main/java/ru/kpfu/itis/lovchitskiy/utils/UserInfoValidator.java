package ru.kpfu.itis.lovchitskiy.utils;

import ru.kpfu.itis.lovchitskiy.entities.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserInfoValidator {

    private final static String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private final static String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,18}$";

    public static boolean checkNickname(String nickname) {
        return nickname.length() > 2 && nickname.length() <= 20;
    }

    public static boolean checkEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean checkPassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    public static boolean checkWeight(float weight) {
        return weight > 20 && weight < 400;
    }

    public static boolean checkHeight(float height) {
        return height > 0 && height < 300;
    }

    public static boolean checkAge(String dateOfBirth) {
        return Integer.parseInt(dateOfBirth.split("-")[0]) > 1901 && Integer.parseInt(dateOfBirth.split("-")[0]) < Calendar.getInstance().get(Calendar.YEAR);
    }
    public static List<String> checkUserInfo(String email, String password){
        return checkUserInfo(null,null,null,null,email,password);
    }
    public static List<String> checkUserInfo(String name, Float weight, Float height, String dateOfBirth, String email, String password){
        List<String> errors = new ArrayList<>();
        if (name != null){
            if (!checkNickname(name)) {
                errors.add("Nickname should be 3-20 length.");
            }
        }
        if (weight != null){
            if (!checkWeight(weight)) {
                errors.add("Weight should be more than 20 and less than 400.");
            }
        }
        if (height != null){
            if (!checkHeight(height)) {
                errors.add("Height should be more than 0 and less than 300.");
            }
        }
        if (dateOfBirth != null){
            if (!checkAge(dateOfBirth)) {
                errors.add("Age should be between 1 and 120.");
            }
        }
        if (email != null){
            if (!checkEmail(email)) {
                errors.add("Email should be like: abc@smth.ye");
            }
        }
        if (password != null){
            if (!checkPassword(password)) {
                errors.add("Password should be 8-18 length, contain 1 letter, number, uppercase and special symbol.");
            }
        }
        return errors;
    }
    public static List<String> checkUserInfo(User user, String password){
        return checkUserInfo(user.getName(),user.getWeight(),user.getHeight(),user.getDateOfBirth(),user.getEmail(),password);
    }
    public static  List<String> checkUserInfo(String name, Float weight, Float height,String dateOfBirth){
        return checkUserInfo(name,weight,height,dateOfBirth,null,null);
    }

}
