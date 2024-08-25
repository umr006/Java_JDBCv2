package edu.school21.chat.Repository;

import edu.school21.chat.entity.Message;

import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id);
}
