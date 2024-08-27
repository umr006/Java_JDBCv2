package edu.school21.chat.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private List<ChatRoom> createdChatRoomList;
    private List<ChatRoom> userSocializesChatroomList;

    public User(long id, String login, String password, List<ChatRoom> createdChatRoomList, List<ChatRoom> userSocializesChatroomList) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdChatRoomList = createdChatRoomList;
        this.userSocializesChatroomList = userSocializesChatroomList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdChatRoomList=" + createdChatRoomList +
                ", userSocializesChatroomList=" + userSocializesChatroomList +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
