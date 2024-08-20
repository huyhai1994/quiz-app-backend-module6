package com.codegym.quizappbackendmodule6.model.dto.quiz_room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuizRoomRequest {
    private Long quizId;
    private Long userId;
    private Integer maxParticipants;
}
