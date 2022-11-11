package ru.kpfu.itis.lovchitskiy.listeners;


import ru.kpfu.itis.lovchitskiy.db.DBExecutor;
import ru.kpfu.itis.lovchitskiy.db.RecipeRepository;
import ru.kpfu.itis.lovchitskiy.db.StatisticRepository;
import ru.kpfu.itis.lovchitskiy.exceptions.DBException;
import ru.kpfu.itis.lovchitskiy.utils.AttributeName;
import ru.kpfu.itis.lovchitskiy.utils.ConnectionProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
            Connection connection = connectionProvider.getConnection();

            DBExecutor dbExecutor = new DBExecutor(connection);
            RecipeRepository recipeRepository = new RecipeRepository(connection);

            StatisticRepository statisticRepository = new StatisticRepository(connection);;
            sce.getServletContext().setAttribute(AttributeName.DBExecutor.value, dbExecutor);
            sce.getServletContext().setAttribute(AttributeName.RecipeRepository.value, recipeRepository);
            sce.getServletContext().setAttribute(AttributeName.StatisticRepository.value, statisticRepository);
        } catch (DBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
