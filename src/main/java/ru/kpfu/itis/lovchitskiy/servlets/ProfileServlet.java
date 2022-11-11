package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;
import ru.kpfu.itis.lovchitskiy.utils.SessionWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(User.USER);
        req.setAttribute(User.USER, user);
        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBExecutor dbExecutor = (DBExecutor) getServletContext().getAttribute(AttributeName.DBExecutor.value);
        User user = (User) req.getSession().getAttribute(User.USER);
        dbExecutor.deleteUser(user);
        SessionWorker.removeUserDataFromSession(req);

        resp.sendRedirect(req.getContextPath() + "/");

    }
}
