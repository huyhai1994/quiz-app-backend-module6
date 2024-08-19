package com.codegym.quizappbackendmodule6.model.dto.quiz_room;

import com.codegym.quizappbackendmodule6.model.QuizRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinQuizRoomResponseDto {
    private String roomCode;
    private String quizTitle;
    private String participantName;
    private LocalDateTime joinedAt;
    private QuizRoom.RoomStatus roomStatus;
    private String errorMessage;

    public JoinQuizRoomResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
