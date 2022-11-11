package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.db.RecipeRepository;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favoriteRecipes")
public class FavoriteRecipesServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipeRepository recipeRepository = (RecipeRepository) getServletContext().getAttribute(AttributeName.RecipeRepository.value);
        User user = (User) req.getSession().getAttribute(User.USER);
        req.setAttribute("recipes", recipeRepository.findAllFavoriteRecipes(user));
        getServletContext().getRequestDispatcher("/WEB-INF/views/favoriteRecipes.jsp").forward(req,resp);
    }
}
