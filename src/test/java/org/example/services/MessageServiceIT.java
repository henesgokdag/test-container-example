package org.example.services;

import org.example.CouchbaseTestContainer;
import org.example.model.Message;
import org.example.repository.MessageRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ActiveProfiles("container")
@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class MessageServiceIT extends CouchbaseTestContainer {
    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void shouldSendAndGetMessage(){
        //given
        Message message = new Message("test-title","test-text");
        messageService.sendMessage(message);
        //when
        Message messageFromRepository = messageService.getById(message.getId());
        //then
        assertEquals("ids not equal",message.getId(),messageFromRepository.getId());
        assertEquals("texts not equal",message.getText(),messageFromRepository.getText());
        assertEquals("titles not equal",message.getTitle(),messageFromRepository.getTitle());
    }
}
