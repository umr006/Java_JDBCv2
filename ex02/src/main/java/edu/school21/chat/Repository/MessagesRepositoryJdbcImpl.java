package edu.school21.chat.Repository;

import edu.school21.chat.Exception.NotSavedSubEntityException;
import edu.school21.chat.entity.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private static Connection connection;

    public MessagesRepositoryJdbcImpl(Connection con) {
        connection = con;
    }

    private final String QUERY_SQL = "select * from s21_chat.message where id = ?";
    private final String FIND_AUTHOR_SQL = "select * from s21_chat.users where id = ?";
    private final String FIND_ROOM_SQL = "select * from s21_chat.chatroom where id = ?";
    private final String INSERT_MESSAGE_SQL = "INSERT INTO s21_chat.message values(DEFAULT, ?,?,?,?)";


    @Override
    public Message save(Message message) throws NotSavedSubEntityException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            if(Optional.ofNullable(findByAuthorId(message.getMessageAuthor().getId())).isEmpty() || Optional.ofNullable(findByRoomId(message.getroom().getId())).isEmpty()) {
                throw new NotSavedSubEntityException("User or ChatRoom not found");
            }
            preparedStatement.setLong(2, message.getMessageAuthor().getId());
            preparedStatement.setLong(3, message.getroom().getId());
            preparedStatement.setString(4, message.getMessageText());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            message.setId(generatedKeys.getLong("id"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public Optional<User> findByAuthorId(Long id) throws NotSavedSubEntityException {
        User user = null;
        //long id, String login, String password, List<ChatRoom> createdChatRoomList, List<ChatRoom> userSocializesChatroomList
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("passwd");
                user = new User(
                        id,
                        login,
                        password,
                        new ArrayList<>(),
                        new ArrayList<>()
                );
            } else {
                throw new NotSavedSubEntityException("Author not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    public Optional<ChatRoom> findByRoomId(Long id)  {
        ChatRoom chatRoom = null;
//        long id, String chatName, long chatOwner, List<Message> messages
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROOM_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String chatName = resultSet.getString("chatroom_name");
                Long chatOwner = resultSet.getLong("chatroom_owner_id");
                chatRoom = new ChatRoom(
                        id,
                        chatName,
                        chatOwner,
                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(chatRoom);
    }
}