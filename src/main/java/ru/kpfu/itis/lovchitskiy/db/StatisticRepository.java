package ru.kpfu.itis.lovchitskiy.db;

import ru.kpfu.itis.lovchitskiy.entities.Statistic;
import ru.kpfu.itis.lovchitskiy.exceptions.DBException;
import ru.kpfu.itis.lovchitskiy.exceptions.StatisticNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticRepository {
    private Connection connection;
    private final String SQL_UPDATE_WHERE_ID = "update statistic set calorie = ?, fat = ?, carb = ?, proteins = ?, last_modified = now() where id = ?";
    private final String SQL_INSERT_INTO_STATISTIC = "insert into statistic (calorie, fat, carb, proteins, last_modified) VALUES (0,0,0,0,now())";
    private final String SQL_SELECT_STATISTIC_FROM_USER_JOIN_STATISTIC = "select s.calorie,s.carb,s.fat,s.proteins from (users inner join user_statistic us on users.id = us.user_id inner join statistic s on us.statistic_id = s.id) where users.id = ? order by s.last_modified desc limit 1;";
    private final String SQL_SELECT_BOOLEAN_FROM_USER_JOIN_STATISTIC = "select extract(days from now()) <= extract(days from s.last_modified) as bool from (users inner join user_statistic us on users.id = us.user_id inner join statistic s on us.statistic_id = s.id) where users.id = ? order by s.last_modified desc limit 1";
    private final String SQL_SELECT_MAX_ID_FROM_STATISTIC = "select max(id) from  statistic";
    private final String SQL_INSERT_INTO_USER_STATISTIC = "insert into user_statistic (user_id, statistic_id) VALUES (?,?)";
    private final String SQL_SELECT_ID_FROM_USER_JOIN_STATISTIC = "select s.id from (users inner join user_statistic us on users.id = us.user_id inner join statistic s on us.statistic_id = s.id) where users.id = ? order by s.last_modified desc limit 1;";

    public StatisticRepository(Connection connection) {
        this.connection = connection;
    }

    public void updateUserStat(int statId, Statistic statistic) {
        try (PreparedStatement updateStatement = connection.prepareStatement(SQL_UPDATE_WHERE_ID)) {

            updateStatement.setFloat(1, statistic.getCalorie());
            updateStatement.setFloat(2, statistic.getFat());
            updateStatement.setFloat(3, statistic.getCarb());
            updateStatement.setFloat(4, statistic.getProteins());
            updateStatement.setInt(5, statId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    public boolean checkThatStatIsToday(int userId) {
        try (PreparedStatement selectStatement = connection.prepareStatement(SQL_SELECT_BOOLEAN_FROM_USER_JOIN_STATISTIC);) {
            selectStatement.setInt(1, userId);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("bool");
            }
            return false;

        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    public Statistic retrieveLastUserStatistic(int userId) {
        try (PreparedStatement selectStatement = connection.prepareStatement(SQL_SELECT_STATISTIC_FROM_USER_JOIN_STATISTIC)) {

            selectStatement.setInt(1, userId);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                return new Statistic(
                        resultSet.getInt("calorie"),
                        resultSet.getInt("fat"),
                        resultSet.getInt("carb"),
                        resultSet.getInt("proteins")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    public int getLastUserStatisticId(int userId) {
        try (PreparedStatement selectStatement = connection.prepareStatement(SQL_SELECT_ID_FROM_USER_JOIN_STATISTIC);) {

            selectStatement.setInt(1, userId);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new StatisticNotFoundException("Not found statistic");
            }
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    public int getLastId() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_MAX_ID_FROM_STATISTIC)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("max");
            }
            return 0;
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    public void createEmptyStat(int userId) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_INTO_STATISTIC)) {
            ps.executeUpdate();
            updateUserStatisticTable(userId, getLastId());
        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }

    private void updateUserStatisticTable(int userId, int statId) {
        try (PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT_INTO_USER_STATISTIC);) {

            insertStatement.setInt(1, userId);
            insertStatement.setInt(2, statId);
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Some problem occurred",e);
        }
    }
}
