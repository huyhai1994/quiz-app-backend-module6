package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.QuizRoom;
import com.codegym.quizappbackendmodule6.model.Result;
import com.codegym.quizappbackendmodule6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByQuizRoom(QuizRoom quizRoom);

    List<Result> findByUser(User user);

    @Query("SELECT COUNT(DISTINCT r.score) + 1 FROM Result r WHERE r.score > :score")
    Long findRankByScore(@Param("score") BigDecimal score);

    List<Result> findByUserIdAndStatus(Long userId, boolean status);
}
