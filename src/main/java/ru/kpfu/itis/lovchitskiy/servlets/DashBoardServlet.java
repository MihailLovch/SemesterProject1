package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        setAttributes(req, ((User) req.getSession().getAttribute(User.USER)));

        getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);
    }

    /**
     * counts user consumed calories, proteins, fats, carbs, proteins, calculates how many left and set them to jsp
     * @param req
     * @param user
     */

    private void setAttributes(HttpServletRequest req, User user) {
        req.setAttribute("caloriePercents", Math.round(user.getConsumedCalories() / user.getCalories() * 100));
        req.setAttribute("fatPercents", Math.round(user.getConsumedFats() / user.getFats() * 100));
        req.setAttribute("carbPercents", Math.round(user.getConsumedCarbs() / user.getCarbs() * 100));
        req.setAttribute("proteinPercents", Math.round(user.getConsumedProteins() / user.getProteins() * 100));

        String absolute;

        absolute = user.getConsumedCalories() < user.getCalories() ? "left " : "over ";
        req.setAttribute("calProgress", absolute + Math.abs(user.getConsumedCalories() - user.getCalories()));

        absolute = user.getConsumedCarbs() < user.getCarbs() ? "left " : "over ";
        req.setAttribute("carbProgress", absolute + Math.abs(user.getConsumedCarbs() - user.getCarbs()));

        absolute = user.getConsumedFats() < user.getFats() ? "left " : "over ";
        req.setAttribute("fatProgress", absolute + Math.abs(user.getConsumedFats() - user.getFats()));

        absolute = user.getConsumedProteins() < user.getProteins() ? "left " : "over ";
        req.setAttribute("proteinProgress", absolute + Math.abs(user.getConsumedProteins() - user.getProteins()));

        req.setAttribute(User.USER, user);


    }
}
