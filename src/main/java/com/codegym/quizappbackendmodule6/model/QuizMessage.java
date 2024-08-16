package com.codegym.quizappbackendmodule6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizMessage {
    private String content;
    private String sender;
    private TrayIcon.MessageType type;

    public enum MessageType {
        JOIN,
        LEAVE,
        CHAT,
        QUIZ_START,
        QUIZ_END,
        ANSWER_SUBMIT
    }
}
