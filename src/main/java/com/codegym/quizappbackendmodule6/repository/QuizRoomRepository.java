package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.QuizRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRoomRepository extends JpaRepository<QuizRoom, Long> {
    Optional<QuizRoom> findByRoomCode(String roomCode);
    List<QuizRoom> findByStatus(QuizRoom.RoomStatus status);
}
