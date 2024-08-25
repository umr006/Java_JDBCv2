package edu.school21.chat.Repository;

import edu.school21.chat.entity.*;
import edu.school21.chat.Main.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private static Connection connection;

    public MessagesRepositoryJdbcImpl(Connection con) {
        connection = con;
    }

    private String QUERY_SQL = "select * from message from id = ?";

    @Override
    public Optional<Message> findById(Long id) {


        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SQL)) {
            preparedStatement.setLong(1, id);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
