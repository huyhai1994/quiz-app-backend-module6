package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;
import com.codegym.quizappbackendmodule6.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {

    @Query(value = "SELECT q.id AS quizzesId, q.title AS quizzesTitle, q.description AS quizzesDescription, u.name AS usersName, u.email AS userEmail, q.time_create AS quizzesTimeCreate " +
            "FROM quizzes q JOIN users u ON q.created_by = u.id" + "ORDER BY q.time_create DESC", nativeQuery = true)
    List<QuizDTO> findQuizDetails();
    List<Quiz> findByTitle(String title);

    @Query(value = "SELECT q.id AS quizzesId, q.title AS quizzesTitle, q.description AS quizzesDescription, u.name AS usersName, q.time_create AS quizzesTimeCreate " +
            "FROM quizzes q JOIN users u ON q.created_by = u.id " +
            "WHERE q.created_by = :userId " +
            "ORDER BY q.time_create DESC",
            nativeQuery = true)
    List<QuizTeacherDTO> findTeacherQuizDetails(@Param("userId") Long userId);

    @Query(value = "SELECT c.name AS Category_Name, q.title AS Quiz_Title, COUNT(r.id) AS Number_Of_Participants " +
            "FROM categories c JOIN quizzes q ON c.id = q.category_id " +
            "LEFT JOIN results r ON q.id = r.quiz_id " +
            "GROUP BY c.name, q.title " +
            "ORDER BY c.name, COUNT(r.id) DESC;",
            nativeQuery = true)
    List<QuizDTO> findQuizzesByCategory();
}
