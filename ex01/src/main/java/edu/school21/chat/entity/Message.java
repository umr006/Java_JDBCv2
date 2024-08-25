package edu.school21.chat.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private long id;
    private long messageAuthor;
    private long roomId;
    private String messageText;
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messageAuthor=" + messageAuthor +
                ", roomId=" + roomId +
                ", messageText='" + messageText + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public Message(long id, long messageAuthor, long roomId, String messageText, LocalDateTime dateTime) {
        this.id = id;
        this.messageAuthor = messageAuthor;
        this.roomId = roomId;
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

    public long getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(long messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
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
