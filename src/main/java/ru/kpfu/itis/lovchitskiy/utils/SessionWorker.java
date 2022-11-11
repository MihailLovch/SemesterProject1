package ru.kpfu.itis.lovchitskiy.utils;

import ru.kpfu.itis.lovchitskiy.entities.User;

import javax.servlet.http.HttpServletRequest;

public class SessionWorker {

    public static void addUserDataToSession(HttpServletRequest req, User user) {
        req.getSession().setAttribute(User.USER, user);
    }

    public static void removeUserDataFromSession(HttpServletRequest req) {
        req.getSession().removeAttribute(User.USER);
    }
}
