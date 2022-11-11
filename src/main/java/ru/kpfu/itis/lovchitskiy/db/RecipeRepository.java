package ru.kpfu.itis.lovchitskiy.db;

import ru.kpfu.itis.lovchitskiy.entities.Recipe;
import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private Connection connection;
    private final String SQL_SELECT_ALL_FROM_RECIPE = "select * from recipe";
    private final String SQL_SELECT_WHERE_ID = "select * from recipe where id = ?";
    private final String SQL_INSERT_INTO_CUSTOM_RECIPES = "insert into custom_recipes (name, calories, proteins, fat, carbohydrates) VALUES (?,?,?,?,?)";
    private final String SQL_INSERT_INTO_USER_RECIPES = "insert into user_recipes (user_id, recipe_id) values (?,?)";
    private final String SQL_SELECT_MAX_ID_FROM_CUSTOM_RECIPES = "select max(id) from  custom_recipes";
    private final String SQL_SELECT_RECIPE_FROM_INNER_JOIN_USER_RECIPES_CUSTOM_RECIPES = "select r.id,name,calories,fat,proteins,carbohydrates from (user_recipes inner join custom_recipes r on r.id = user_recipes.recipe_id) where user_id = ?";
    private final String SQL_SELECT_PREFERRED_RECIPES = "select r.id as id, name,calories,proteins,fat,carbohydrates from (preferred_recipes inner join  recipe r on preferred_recipes.recipe_id = r.id) where user_id = ?";
    private final String SQL_INSERT_INTO_PREFERRED_RECIPE = "insert into preferred_recipes (user_id, recipe_id) VALUES (?,?)";

    public RecipeRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement selectAll = connection.prepareStatement(SQL_SELECT_ALL_FROM_RECIPE)) {

            ResultSet resultSet = selectAll.executeQuery();
            while (resultSet.next()) {
                recipes.add(getRecipeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
        return recipes;
    }

    public List<Recipe> findAllCustom(User user) {
        List<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement selectAll = connection.prepareStatement(SQL_SELECT_RECIPE_FROM_INNER_JOIN_USER_RECIPES_CUSTOM_RECIPES)) {
            selectAll.setInt(1, user.getId());
            ResultSet resultSet = selectAll.executeQuery();
            while (resultSet.next()) {
                recipes.add(getRecipeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
        return recipes;
    }

    public Recipe findRecipe(int id) {
        try (PreparedStatement selectOne = connection.prepareStatement(SQL_SELECT_WHERE_ID);) {

            selectOne.setInt(1, id);
            ResultSet resultSet = selectOne.executeQuery();
            resultSet.next();
            return getRecipeFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    public void addUserRecipe(Recipe recipe, User user) {
        try (PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT_INTO_CUSTOM_RECIPES)) {
            insertStatement.setString(1, recipe.getName());
            insertStatement.setInt(2, recipe.getCalories());
            insertStatement.setInt(3, recipe.getProteins());
            insertStatement.setInt(4, recipe.getFats());
            insertStatement.setInt(5, recipe.getCarbohydrates());
            insertStatement.executeUpdate();
            updateUserRecipesTable(user);
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    public void updateUserRecipesTable(User user) {
        try (PreparedStatement insertStatement2 = connection.prepareStatement(SQL_INSERT_INTO_USER_RECIPES)) {
            int recipe_id = getLastId();
            insertStatement2.setInt(1, user.getId());
            insertStatement2.setInt(2, recipe_id);
            insertStatement2.executeQuery();
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }
    public List<Recipe> findAllFavoriteRecipes(User user){
        List<Recipe> recipes = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PREFERRED_RECIPES)){
            preparedStatement.setInt(1,user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                recipes.add(getRecipeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
        return recipes;
    }
    public void addFavoriteRecipe(User user, Recipe recipe){
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_PREFERRED_RECIPE)) {
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setInt(2,recipe.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("This recipe is already preferred",e);
        }
    }
    private int getLastId() {
        try (PreparedStatement selectMax = connection.prepareStatement(SQL_SELECT_MAX_ID_FROM_CUSTOM_RECIPES)) {
            ResultSet resultSet = selectMax.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("max");
            }
            return 0;
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    private Recipe getRecipeFromResultSet(ResultSet resultSet) throws SQLException {
        return new Recipe(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("calories"),
                resultSet.getInt("proteins"),
                resultSet.getInt("fat"),
                resultSet.getInt("carbohydrates")
        );
    }
}
