package edu.school21.chat.Main;

import edu.school21.chat.Repository.MessagesRepository;
import edu.school21.chat.Repository.MessagesRepositoryJdbcImpl;
import edu.school21.chat.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Main {
    private static final String DB_URL = Application.get("db.url");
    private static final String DB_LOGIN = Application.get("db.login");
    private static final String DB_PASSWORD = Application.get("db.password");

    private static String QUERY_SQL = "select id, login, passwd from s21_chat.user";

    public static void main(String[] args) {


        try (Connection connection = DataSource.getConnection()) {
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(connection);
            User user = new User(1, "login")

            var opMessage = messagesRepository.save();
//            Message message = opMessage.get();
//            Optional<Long> messageAuthor = Optional.ofNullable(message.getMessageAuthor());
//            messageAuthor.ifPresentOrElse( word -> {
//                System.out.println("Author id = " + messageAuthor.get());
//            }, () -> System.out.println("Author not Found"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}