package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.utils.SessionWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signOut")
public class SignOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionWorker.removeUserDataFromSession(req);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
