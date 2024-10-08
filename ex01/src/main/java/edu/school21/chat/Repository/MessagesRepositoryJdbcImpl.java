package edu.school21.chat.Repository;

import edu.school21.chat.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public Optional<Message> findById(Long id) {
        Message message = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long messageAuthor = resultSet.getLong("message_author");
                Long roomId = resultSet.getLong("room_id");
                String messageText = resultSet.getString("message_text");
                LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();

                message = new Message(
                        id,
                        messageAuthor,
                        roomId,
                        messageText,
                        dateTime
                );

                User user = findByAuthorId(messageAuthor).get();
                ChatRoom chatRoom = findByRoomId(roomId).get();

                System.out.println(
                        "Message : { \n" + message.getId() + "\n" +
                                "author = {" + user + "},\n" +
                                "room = {" + chatRoom + "},\n" +
                                "text = {" + message.getMessageText() + "},\n" +
                                "dateTime = {" + message.getDateTime() + "}"
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(message);
    }


    public Optional<User> findByAuthorId(Long id) {
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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    public Optional<ChatRoom> findByRoomId(Long id) {
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