package com.example.webapp.controller;

import com.example.webapp.entity.Message;
import com.example.webapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;


    @GetMapping
    public List<Message> getMessages() {
        System.out.println(messageRepository.findAll());
        return messageRepository.findAll();
    }
}
