package org.example.controller;

import org.example.model.ComingMessage;
import org.example.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.services.MessageService;


@RestController
@RequestMapping(value = "/api")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {

        this.messageService = messageService;
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable String id) {
        Message message = messageService.getById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/saveMessage")
    public ResponseEntity<Message> saveMessage(@RequestBody ComingMessage comingMessage) {
        Message message = new Message(comingMessage.getTitle(),comingMessage.getText());
        messageService.sendMessage(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
