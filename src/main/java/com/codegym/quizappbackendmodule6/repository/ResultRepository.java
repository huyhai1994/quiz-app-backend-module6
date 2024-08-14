package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Result;
import com.codegym.quizappbackendmodule6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result,Long> {
    List<Result> findByUser(User user);

    @Query("SELECT COUNT(r) + 1 FROM Result r WHERE r.score > :score")
    int findRankByScore(@Param("score") Long score);

}
