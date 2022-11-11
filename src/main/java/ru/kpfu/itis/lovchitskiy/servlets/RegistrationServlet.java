package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.db.StatisticRepository;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.exceptions.UserUniqueException;
import ru.kpfu.itis.lovchitskiy.services.AuthenticationService;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;
import ru.kpfu.itis.lovchitskiy.utils.SessionWorker;
import ru.kpfu.itis.lovchitskiy.utils.UserInfoValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * More complex example. It looks more like real code.
 * <p>
 * It also can be mapped to "/" and become "Default servlet"
 * Default servlet handles any request that is not handled by another servlets.
 * So static files won't be served. For them "File Servlet" can be implemented.
 * It must handle any request that looks like request for static content:
 * try to find file and read it from drive, write to response.
 */
@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("display","none");
        getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String notice;
        User user;
        String name = req.getParameter(User.NAME_KEY);
        String password = req.getParameter(User.PASSWORD_KEY);
        String email = req.getParameter(User.EMAIL_KEY);
        String weight = req.getParameter(User.WEIGHT_KEY);
        String height = req.getParameter(User.HEIGHT_KEY);
        String dateOfBirth = req.getParameter(User.AGE_KEY);
        String sex = req.getParameter(User.SEX_KEY);
        StatisticRepository stRep = (StatisticRepository) getServletContext().getAttribute(AttributeName.StatisticRepository.value);
        DBExecutor dbExecutor = (DBExecutor) req.getServletContext().getAttribute(AttributeName.DBExecutor.value);

        if (req.getParameter("login") != null) {
            AuthenticationService.logInUser(req, resp, "/WEB-INF/views/registration.jsp", dbExecutor, stRep);
            return;
        }
        if (name != null && !name.isEmpty() &&
                password != null && !password.isEmpty() &&
                email != null && !email.isEmpty() &&
                weight != null && !weight.isEmpty() &&
                height != null && !height.isEmpty() &&
                dateOfBirth != null && !dateOfBirth.isEmpty() &&
                sex != null && !sex.isEmpty()
        ) {
            try {
                user = new User(req);
                List<String> errors = UserInfoValidator.checkUserInfo(user, req.getParameter(User.PASSWORD_KEY));
                if (errors.isEmpty()) {
                    notice = dbExecutor.addToDb(user);
                    req.setAttribute(AttributeName.Notice.value, notice);
                    user = dbExecutor.getUser(user.getEmail());
                    SessionWorker.addUserDataToSession(req, user);
                    resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                    return;
                } else {
                    req.setAttribute("errors", errors.toArray());
                    req.setAttribute("name", name);
                    req.setAttribute("email", email);
                    req.setAttribute("weight", weight);
                    req.setAttribute("height", height);

                }

            } catch (UserUniqueException ex) {
                req.setAttribute(AttributeName.Error.value, "user exists");
            } catch (Exception ex) {
                req.setAttribute(AttributeName.Error.value, ex.getMessage());
            }
        } else {
            req.setAttribute(AttributeName.Error.value, "All fields should be filled");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);

    }

}