package edu.school21.chat.Repository;

import edu.school21.chat.Exception.NotSavedSubEntityException;
import edu.school21.chat.entity.Message;

import java.util.Optional;

public interface MessagesRepository {
    Message save(Message message) throws NotSavedSubEntityException;
}
