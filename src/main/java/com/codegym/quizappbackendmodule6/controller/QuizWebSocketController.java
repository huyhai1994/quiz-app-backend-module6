package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.QuizMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class QuizWebSocketController {

    @MessageMapping("/quiz.sendMessage")
    @SendTo("/topic/public")
    public QuizMessage sendMessage(@Payload QuizMessage quizMessage) {
        return quizMessage;
    }

    @MessageMapping("/quiz.addUser")
    @SendTo("/topic/public")
    public QuizMessage addUser(@Payload QuizMessage quizMessage) {
        return quizMessage;
    }
}
