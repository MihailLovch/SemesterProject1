package ru.kpfu.itis.lovchitskiy.utils;

import ru.kpfu.itis.lovchitskiy.exceptions.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static ConnectionProvider instance;
    public static ConnectionProvider getInstance() throws DBException{
        if(instance == null){
            instance = new ConnectionProvider();
        }
        return instance;
    }
    private Connection connection;

    private ConnectionProvider() throws DBException{
        try {
            Properties properties =new Properties();
            try {
                Class.forName("org.postgresql.Driver");
                properties.load(new FileInputStream("C:\\Users\\MihailLovch\\IdeaProjects\\SemesterProject\\src\\main\\resources\\db.properties"));
            } catch (ClassNotFoundException | IOException e) {
                throw new IllegalArgumentException(e);
            }
            connection =  DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );
        } catch (SQLException e) {
            throw new DBException("Can't connect to DB",e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
