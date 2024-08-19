package com.codegym.quizappbackendmodule6.model;

import com.codegym.quizappbackendmodule6.model.dto.quiz_room.QuizResultDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizMessage {
    private String content;
    private String sender;
    private MessageType type;
    private String roomCode;
    private List<QuizResultDto> results;

    public QuizMessage(String content, String sender, MessageType type, String roomCode) {
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.roomCode = roomCode;
    }

    public enum MessageType {
        JOIN,
        LEAVE,
        CHAT,
        QUIZ_START,
        QUIZ_END,
        QUIZ_RESTART,
        ANSWER_SUBMIT,
        ERROR,
    }
}
