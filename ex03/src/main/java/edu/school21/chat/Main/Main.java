package edu.school21.chat.Main;

import edu.school21.chat.Repository.MessagesRepository;
import edu.school21.chat.Repository.MessagesRepositoryJdbcImpl;
import edu.school21.chat.entity.*;

import java.sql.*;
import java.time.LocalDateTime;
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
            MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(connection);


            Optional<Message> optionalMessage = messagesRepository.findById(6L);
            User user = new User(3, "login", "passwd", new ArrayList<>(), new ArrayList<>());
            if (optionalMessage.isPresent()) {
                Message message = optionalMessage.get();
                message.setMessageText("New Update TEXT Old_text: 'New Update Message' v2");
                message.setDateTime(LocalDateTime.now());
                message.setMessageAuthor(user);
                messagesRepository.update(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}