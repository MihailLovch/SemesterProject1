package ru.kpfu.itis.lovchitskiy.servlets;


import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.db.StatisticRepository;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.services.AuthenticationService;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;
import ru.kpfu.itis.lovchitskiy.utils.SessionWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/enter")
public class EnteringServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/enter.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBExecutor dbExecutor = (DBExecutor) getServletContext().getAttribute(AttributeName.DBExecutor.value);
        StatisticRepository stRep = (StatisticRepository) getServletContext().getAttribute(AttributeName.StatisticRepository.value);
        AuthenticationService.logInUser(req, resp, "/WEB-INF/views/enter.jsp", dbExecutor,stRep);
    }
}
