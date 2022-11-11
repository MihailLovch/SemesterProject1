package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.db.RecipeRepository;
import ru.kpfu.itis.lovchitskiy.db.StatisticRepository;
import ru.kpfu.itis.lovchitskiy.entities.Recipe;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.exceptions.DBException;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;
import ru.kpfu.itis.lovchitskiy.services.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recipes")
public class RecipesServlet extends HttpServlet {
    private RecipeRepository recipeRepository;
    private DBExecutor dbExecutor;
    private StatisticRepository stRep;
    private User user;
    @Override
    public void init() throws ServletException {
        recipeRepository = (RecipeRepository) getServletContext().getAttribute(AttributeName.RecipeRepository.value);
        stRep = (StatisticRepository) getServletContext().getAttribute(AttributeName.StatisticRepository.value);
        dbExecutor = (DBExecutor) getServletContext().getAttribute(AttributeName.DBExecutor.value);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Recipe> recipes = recipeRepository.findAll();
        int baseRecipesAmount = recipes.size();
        req.setAttribute("baseRecipesAmount", baseRecipesAmount);
        user = (User) req.getSession().getAttribute(User.USER);
        if (user != null){
            recipes.addAll(recipeRepository.findAllCustom(user));
        }
        req.setAttribute("display", "none");
        req.setAttribute("recipes", recipes.toArray(new Recipe[0]));
        getServletContext().getRequestDispatcher("/WEB-INF/views/recipes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Recipe> recipes = recipeRepository.findAll();
        int baseRecipesAmount = recipes.size();
        req.setAttribute("baseRecipesAmount", baseRecipesAmount);
        user = (User) req.getSession().getAttribute(User.USER);
        if (user!=null) {
            recipes.addAll(recipeRepository.findAllCustom(user));
        }
        if (req.getParameter("login") != null) {
            AuthenticationService.logInUser(req, resp, "/WEB-INF/views/recipes.jsp", dbExecutor, stRep);
            return;
        }
        for (int i = 0; i < recipes.size(); i++) {
            if (req.getParameter("preferred"+i) != null){
                try {
                    recipeRepository.addFavoriteRecipe(user, recipes.get(i));
                }catch (DBException e){
                    req.setAttribute("error",e.getMessage());
                }
                break;
            }
            if (req.getParameter("submit"+i) != null) {
                user = (User) req.getSession().getAttribute(User.USER);
                int userId = dbExecutor.getUserId(user);
                Recipe recipe = recipes.get(i);
                int grams = Integer.parseInt(req.getParameter("grams"+i));
                user.eatFood(recipe,grams);
                stRep.updateUserStat(stRep.getLastUserStatisticId(userId), user.getStatistic());
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/recipes?added=true");
    }
}
