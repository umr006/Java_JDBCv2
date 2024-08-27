package edu.school21.chat.Repository;

import edu.school21.chat.Exception.NotSavedSubEntityException;
import edu.school21.chat.entity.Message;

import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id);
    Optional<Message> save(Message message) throws NotSavedSubEntityException;
    void update(Message message);
}
