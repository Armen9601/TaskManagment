package manager;

import db.DBConnectionProvider;

import enums.Status;
import model.Task;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public boolean addTask(Task task) {
        String sql = "INSERT INTO tasks(title,description,user_id,status, deadline) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setInt(3, task.getUserId());
            statement.setString(4, String.valueOf(task.getStatus()));
            statement.setString(5, task.getDeadline());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                task.setId(generatedKeys.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Task getByID(int id) {
        String sql = "SELECT * FROM tasks where id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getTasksFromResultSet(resultSet);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Task> getByUserId(int id) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks where user_id=" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTasksFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }


//    public User getByEmailAndPassword(String email, String password) {
//        String sql = "SELECT * FROM users where email=? and password=?";
//        try {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, email);
//            statement.setString(2, password);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return getUsersFromResultSet(resultSet);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

    public Task getByTitle(String title) {
        String sql = "SELECT * FROM tasks where title=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getTasksFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(getTasksFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }

    private Task getTasksFromResultSet(ResultSet resultSet) {
        try {
            return Task.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .userId(resultSet.getInt(4))
                    .status(Status.valueOf(resultSet.getString(5)))
                    .deadline(resultSet.getString(6))
                    .build();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTaskByUserId(int userId) {
        String sql = "DELETE FROM tasks where user_id=" + userId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
