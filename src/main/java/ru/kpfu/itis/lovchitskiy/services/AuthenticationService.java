package ru.kpfu.itis.lovchitskiy.services;

import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.db.StatisticRepository;
import ru.kpfu.itis.lovchitskiy.entities.Statistic;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;
import ru.kpfu.itis.lovchitskiy.utils.SessionWorker;
import ru.kpfu.itis.lovchitskiy.utils.UserInfoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AuthenticationService {

    /**
     * This method checks if user with this email and password exists, adds him to session if yes and sets his statistic if has current day info, else
     * sets everything to 0 and add this to statistic table, and then does forward
     *
     * @param req        request
     * @param resp       response
     * @param direction  location of jsp file to forward
     * @param dbExecutor dbExecutor
     * @param stRep      StatisticRepository
     */
    public static void logInUser(HttpServletRequest req, HttpServletResponse resp, String direction, DBExecutor dbExecutor, StatisticRepository stRep) throws ServletException, IOException {
        if (req.getParameter(User.PASSWORD_KEY) != null && !req.getParameter(User.PASSWORD_KEY).isEmpty() &&
                req.getParameter(User.EMAIL_KEY) != null && !req.getParameter(User.EMAIL_KEY).isEmpty()) {
            List<String> errors = UserInfoValidator.checkUserInfo(req.getParameter(User.EMAIL_KEY), req.getParameter(User.PASSWORD_KEY));
            if (errors.isEmpty()) {
                try {
                    User user = new User(req.getParameter(User.EMAIL_KEY), req.getParameter(User.PASSWORD_KEY));
                    if (dbExecutor.findUser(user)) {
                        user = dbExecutor.getUser(user.getEmail());
                        int userId = user.getId();
                        Statistic statistic = stRep.retrieveLastUserStatistic(userId);
                        if (statistic == null || !stRep.checkThatStatIsToday(userId)) {
                            user.setStatistic(new Statistic(0, 0, 0, 0));
                            stRep.createEmptyStat(userId);
                        } else {
                            user.setStatistic(statistic);
                        }
                        SessionWorker.addUserDataToSession(req, user);
                        resp.sendRedirect(req.getServletContext().getContextPath() + "/profile");
                        return;
                    } else {
                        req.setAttribute("logError", "Can't find user with this email or password");
                    }
                } catch (RuntimeException e) {
                    req.setAttribute("logError", "Server side problem occurred, try later");
                }
            }else{
                req.setAttribute("logErrors",errors.toArray(new String[0]));
            }
        } else {
            req.setAttribute("logError", "All fields should be filled");
        }
        req.setAttribute("display", "block");
        req.setAttribute("logEmail", req.getParameter(User.EMAIL_KEY));
        req.getServletContext().getRequestDispatcher(direction).forward(req, resp);
    }
}
