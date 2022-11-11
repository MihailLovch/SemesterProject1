package ru.kpfu.itis.lovchitskiy.filters;

import ru.kpfu.itis.lovchitskiy.entities.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/dashboard/*", "/profile/*","/favoriteRecipes/*"})
public class UserFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute(User.USER);
        if (user != null) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/enter");
        }
    }

    @Override
    public void destroy() {
    }
}
