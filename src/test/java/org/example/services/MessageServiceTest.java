package org.example.services;

import org.example.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.example.repository.MessageRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.springframework.test.util.AssertionErrors.*;

@RunWith(SpringRunner.class)
public class MessageServiceTest {
    @MockBean
    private MessageRepository messageRepository;

    public MessageService messageService;

    @Test
    public void shouldSendMessage(){
        //given
        Message message = new Message("test-title","test-text");

        //when
        messageService.sendMessage(message);

        //then
        Mockito.verify(messageRepository,times(1)).save(message);
    }

    @Test
    public void shouldGetMessage(){
        //given
        Message message = new Message("test-title","test-text");
        //when
        Mockito.when(messageRepository.findMessageById(message.getId())).thenReturn(message);
        Message messageFromRepository = messageService.getById(message.getId());
        //then
        assertEquals("ids not equal",message.getId(),messageFromRepository.getId());
        assertEquals("texts not equal",message.getText(),messageFromRepository.getText());
        assertEquals("titles not equal",message.getTitle(),messageFromRepository.getTitle());
    }

    @Before
    public void setup(){
        messageService = new MessageService(messageRepository);
    }
}
