package ru.kpfu.itis.lovchitskiy.db;


import ru.kpfu.itis.lovchitskiy.entities.User;
import ru.kpfu.itis.lovchitskiy.exceptions.DBException;
import ru.kpfu.itis.lovchitskiy.exceptions.UserNotFoundException;
import ru.kpfu.itis.lovchitskiy.exceptions.UserUniqueException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class that works with Users table
 */
public class DBExecutor {

    private Connection connection;

    private final String SQL_INSERT_INTO_USERS = "insert into users (nickname, email, sex, weight, height, date_of_birth, password) values (?,?,?,?,?,?,?)";
    private final String SQL_SELECT_FROM_USER_BY_EMAIL = "select *,extract(year from age(now(),users.date_of_birth)) as age from users where email = ?";
    private final String SQL_SELECT_FROM_USER_BY_ALL_PARAMS = "select * from users where email = ? and password = ?";
    private final String SQL_SELECT_ALL_FROM_USERS = "select *,extract(year from age(now(),users.age)) as age from users";
    private final String SQL_UPDATE_WHERE_EMAIL = "update users set nickname = ?, sex = ?, weight = ?, height = ?, date_of_birth = ? where email = ?";
    private final String SQL_DELETE_FROM_USERS_WHERE_EMAIL = "delete from users where email = ?";

    private final String MESSAGE = "Info has been written to DB.";

    public DBExecutor(Connection connection) {
        this.connection = connection;
    }

    /**
     * adds user data to table users or throw UserUniqueException if user with such email already exists
     *
     * @param user which you want to add to db
     * @return
     * @throws DBException
     */

    public String addToDb(User user) {
        try (PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT_INTO_USERS)) {
            insertStatement.setString(1, user.getName());
            insertStatement.setString(2, user.getEmail());
            insertStatement.setBoolean(3, user.getSex());
            insertStatement.setFloat(4, user.getWeight());
            insertStatement.setFloat(5, user.getHeight());
            insertStatement.setObject(6, user.getDateOfBirthAsTimestamp());
            insertStatement.setString(7, user.getPassword());

            if (checkUniqueEmail(user.getEmail())) {
                insertStatement.executeUpdate();
            } else {
                throw new UserUniqueException();
            }

        } catch (SQLException e) {
            throw new DBException("Can't add user", e);
        }
        return MESSAGE;
    }

    public boolean checkUniqueEmail(String email) {
        try (PreparedStatement selectStatement = connection.prepareStatement(SQL_SELECT_FROM_USER_BY_EMAIL)) {
            selectStatement.setString(1, email);
            return nothingFoundInTable(selectStatement);
        } catch (SQLException e) {
            throw new DBException("Some problem occurred ", e);
        }
    }

    public boolean findUser(User user) {
        try (PreparedStatement findUserStatement = connection.prepareStatement(SQL_SELECT_FROM_USER_BY_ALL_PARAMS)) {

            findUserStatement.setString(1, user.getEmail());
            findUserStatement.setString(2, user.getPassword());
            return !nothingFoundInTable(findUserStatement);
        } catch (SQLException e) {
            throw new DBException("Some problem occurred", e);
        }
    }

    public void updateUser(User user) {
        try (PreparedStatement updateStatement = connection.prepareStatement(SQL_UPDATE_WHERE_EMAIL)) {

            updateStatement.setString(1, user.getName());
            updateStatement.setBoolean(2, user.getSex());
            updateStatement.setFloat(3, user.getWeight());
            updateStatement.setFloat(4, user.getHeight());
            updateStatement.setObject(5, user.getDateOfBirthAsTimestamp());
            updateStatement.setString(6, user.getEmail());
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Some problem occurred", e);
        }
    }

    public void deleteUser(User user) {
        try (PreparedStatement deleteStatement = connection.prepareStatement(SQL_DELETE_FROM_USERS_WHERE_EMAIL)) {

            deleteStatement.setString(1, user.getEmail());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Some problem occurred", e);
        }
    }

    public User getUser(String email) {
        try (PreparedStatement getUserStatement = connection.prepareStatement(SQL_SELECT_FROM_USER_BY_EMAIL)) {

            getUserStatement.setString(1, email);
            ResultSet resultSet = getUserStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException("Some problem occurred", e);
        }
        throw new UserNotFoundException("Not found");
    }

    public User[] findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement getAllUsers = connection.prepareStatement(SQL_SELECT_ALL_FROM_USERS)) {

            ResultSet resultSet = getAllUsers.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException("Some problem occurred", e);
        }
        return users.toArray(new User[0]);
    }

    public int getUserId(User user) {
        try (PreparedStatement getUserStatement = connection.prepareStatement(SQL_SELECT_FROM_USER_BY_EMAIL);) {

            getUserStatement.setString(1, user.getEmail());
            ResultSet resultSet = getUserStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new DBException("Some problem occurred", e);
        }
        throw new UserNotFoundException("Not found");
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getInt("id"),
                resultSet.getString("nickname"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getBoolean("sex"),
                resultSet.getFloat("weight"),
                resultSet.getFloat("height"),
                resultSet.getString("date_of_birth")
        );
        user.setAge(resultSet.getInt("age"));
        return user;
    }

    private boolean nothingFoundInTable(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        return !resultSet.isBeforeFirst();
    }

}
