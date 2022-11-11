package ru.kpfu.itis.lovchitskiy.servlets;

import ru.kpfu.itis.lovchitskiy.db.RecipeRepository;
import ru.kpfu.itis.lovchitskiy.entities.Recipe;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipes/add")
public class AddRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/addRecipe.jsp").forward(req, resp);
    }

    /**
     *  shows page in which user can add his custom recipe
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RecipeRepository recipeRepository = (RecipeRepository) getServletContext().getAttribute(AttributeName.RecipeRepository.value);

        Recipe recipe = new Recipe(
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("calorie")),
                Integer.parseInt(req.getParameter("protein")),
                Integer.parseInt(req.getParameter("fat")),
                Integer.parseInt(req.getParameter("carb"))
        );

        recipeRepository.addUserRecipe(recipe,(User)req.getSession().getAttribute(User.USER));

        resp.sendRedirect(getServletContext().getContextPath() + "/recipes");
    }

}
