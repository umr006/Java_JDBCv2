package edu.school21.chat.Main;

import edu.school21.chat.entity.*;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static final String DB_URL = Application.get("db.url");
    private static final String DB_LOGIN = Application.get("db.login");
    private static final String DB_PASSWORD = Application.get("db.password");

    private static String QUERY_SQL = "select id, login, passwd from s21_chat.user";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user1 = new User(resultSet.getLong("id"), resultSet.getString("login"), resultSet.getString("passwd"), new ArrayList<>(), new ArrayList<>());
            System.out.println(user1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}