package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;
import ru.kpfu.itis.lovchitskiy.utils.UserInfoValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile/edit")
public class ProfileEditServlet extends HttpServlet {
    private String name;
    private String weight;
    private String height;
    private String dateOfBirth;
    private String sex;
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/profileEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBExecutor dbExecutor = (DBExecutor) req.getServletContext().getAttribute(AttributeName.DBExecutor.value);

        name = req.getParameter(User.NAME_KEY);
        weight = req.getParameter(User.WEIGHT_KEY);
        height = req.getParameter(User.HEIGHT_KEY);
        dateOfBirth = req.getParameter(User.AGE_KEY);
        sex = req.getParameter(User.SEX_KEY);
        if (
                name != null && !name.isEmpty() &&
                        weight != null && !weight.isEmpty() &&
                        height != null && !height.isEmpty() &&
                        dateOfBirth != null && !dateOfBirth.isEmpty() &&
                        sex != null && !sex.isEmpty()

        ) {
            List<String> errors = UserInfoValidator.checkUserInfo(name,Float.parseFloat(weight),Float.parseFloat(height),dateOfBirth);
            if (errors.isEmpty()) {
                user = (User) req.getSession().getAttribute(User.USER);
                user.setName(name);
                user.setWeight(Float.parseFloat(weight));
                user.setHeight(Float.parseFloat(height));
                user.setDateOfBirth(dateOfBirth);
                user.setSex(Boolean.valueOf(sex));
                dbExecutor.updateUser(user);
                user = dbExecutor.getUser(user.getEmail());
            } else {
                req.setAttribute("errors", errors);
                doGet(req, resp);
                return;
            }
        } else {
            req.setAttribute("notice", "All fields should be filled");
            doGet(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
