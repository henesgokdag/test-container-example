package org.example.repository;

import org.example.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message,String> {
    Message findMessageById(String id);
}
