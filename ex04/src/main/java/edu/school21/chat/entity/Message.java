package edu.school21.chat.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private long id;
    private User messageAuthor;
    private ChatRoom room;
    private String messageText;
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messageAuthor=" + messageAuthor +
                ", room=" + room +
                ", messageText='" + messageText + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public Message(long id, User messageAuthor, ChatRoom room, String messageText, LocalDateTime dateTime) {
        this.id = id;
        this.messageAuthor = messageAuthor;
        this.room = room;
        this.messageText = messageText;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(User messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public ChatRoom getroom() {
        return room;
    }

    public void setroom(ChatRoom room) {
        this.room = room;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
