package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.db.StatisticRepository;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;
import ru.kpfu.itis.lovchitskiy.services.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")

public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setButtonsURl(req);
        req.setAttribute("display", "none");
        req.setAttribute("email", "");
        getServletContext().getRequestDispatcher("/WEB-INF/views/mainpage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBExecutor dbExecutor = (DBExecutor) getServletContext().getAttribute(AttributeName.DBExecutor.value);
        StatisticRepository stRep = (StatisticRepository) getServletContext().getAttribute(AttributeName.StatisticRepository.value);
        AuthenticationService.logInUser(req, resp, "/WEB-INF/views/mainpage.jsp", dbExecutor, stRep);

    }

    private void setButtonsURl(HttpServletRequest req) {
        req.setAttribute("reg", getServletContext().getContextPath() + "/reg");
    }
}
