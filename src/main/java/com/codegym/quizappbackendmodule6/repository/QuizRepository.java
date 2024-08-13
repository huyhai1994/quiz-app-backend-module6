package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query(value = "SELECT q.id AS quizzesid, q.title AS quizzestitle, q.description AS quizzesdescription, u.name AS usersname, u.email AS useremail, q.time_create AS quizzestimecreate " +
            "FROM quizzes q JOIN users u ON q.created_by = u.id" + "order BY q.time_create DESC", nativeQuery = true)
    List<QuizDTO> findQuizDetails();

    @Query(value = "SELECT q.id AS quizzesid, q.title AS quizzestitle, q.description AS quizzesdescription, u.name AS usersname, q.time_create AS quizzestimecreate " +
            "FROM quizzes q JOIN users u ON q.created_by = u.id " +
            "WHERE q.created_by = :userid " +
            "ORDER BY q.time_create DESC",
            nativeQuery = true)
    List<QuizTeacherDTO> findTeacherQuizDetails(@Param("userId") Long userId);


    @Query(value = "SELECT q.id AS id, q.title AS title, q.quantity AS quantity FROM quizzes q", nativeQuery = true)
    List<QuizStudentDTO> findAllQuizzesWithDTO();


    Optional<Quiz> findByTitle(String title);
}
