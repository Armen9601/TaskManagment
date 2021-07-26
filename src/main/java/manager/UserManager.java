package manager;

import db.DBConnectionProvider;
import enums.UserType;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public boolean register(User user) {
        String sql = "INSERT INTO users(name,surname,email,password,pic_url,biography) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPicUrl());
            statement.setString(6, user.getBiography());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public User getByID(long id) {
        String sql = "SELECT * FROM users where id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getUsersFromResultSet(resultSet);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users where email=? and password=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUsersFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM users where email=? and password=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUsersFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE usertype='USER'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUsersFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    private User getUsersFromResultSet(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surName(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .picUrl(resultSet.getString(6))
                    .biography(resultSet.getString(7))
                    .userType(UserType.valueOf(resultSet.getString(8)))
                    .build();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM users where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("Update users set name='%s', surname='%s',email='%s',password='%s',pic_url='%s',biograph='%s' WHERE id=" + user.getId(),
                    user.getName(), user.getSurName(), user.getEmail(), user.getPassword(), user.getPicUrl(), user.getBiography());
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
