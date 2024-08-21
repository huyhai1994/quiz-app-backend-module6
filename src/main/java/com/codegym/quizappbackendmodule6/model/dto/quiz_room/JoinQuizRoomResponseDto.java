package com.codegym.quizappbackendmodule6.model.dto.quiz_room;

import com.codegym.quizappbackendmodule6.model.QuizRoom;
import com.codegym.quizappbackendmodule6.model.QuizSession;
import com.codegym.quizappbackendmodule6.model.User;
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

    public static JoinQuizRoomResponseDto fromQuizRoom(QuizRoom quizRoom, Long userId) {
        User participant = quizRoom.getQuizSessions().stream()
                .map(QuizSession::getUser)
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found in this quiz room"));

        return new JoinQuizRoomResponseDto(
                quizRoom.getRoomCode(),
                quizRoom.getQuiz().getTitle(),
                participant.getName(),
                LocalDateTime.now(),
                quizRoom.getStatus(),
                null
        );
    }
}