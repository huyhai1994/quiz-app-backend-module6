package com.codegym.quizappbackendmodule6.model.dto.quiz_room;

import com.codegym.quizappbackendmodule6.model.QuizRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRoomResponseDto {
    private Long id;
    private String roomCode;
    private String quizTitle;
    private String creatorName;
    private LocalDateTime createdAt;
    private QuizRoom.RoomStatus status;
    private Integer maxParticipants;
    private Integer currentParticipants;

    public static QuizRoomResponseDto fromQuizRoom(QuizRoom quizRoom) {
        return new QuizRoomResponseDto(
                quizRoom.getId(),
                quizRoom.getRoomCode(),
                quizRoom.getQuiz().getTitle(),
                quizRoom.getCreateBy().getName(),
                quizRoom.getCreatedAt(),
                quizRoom.getStatus(),
                quizRoom.getMaxParticipants(),
                quizRoom.getCurrentParticipants()
        );
    }
}
