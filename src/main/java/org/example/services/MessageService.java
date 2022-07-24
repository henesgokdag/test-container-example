package org.example.services;

import org.example.model.Message;
import org.springframework.stereotype.Service;
import org.example.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message getById(String id) {
        return  messageRepository.findMessageById(id);
    }

    public void sendMessage(Message message) {
        messageRepository.save(message);
    }
}
