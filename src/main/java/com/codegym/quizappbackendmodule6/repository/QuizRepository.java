package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.dto.QuizTimeDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizNameDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;
import com.codegym.quizappbackendmodule6.model.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query(value = "SELECT q.id AS quizzesid, q.title AS quizzestitle, q.description AS quizzesdescription, u.name AS usersname, u.email AS useremail, q.time_create AS quizzestimecreate " +
            "FROM quizzes q JOIN users u ON q.created_by = u.id" + " order BY q.time_create DESC", nativeQuery = true)
    List<QuizDTO> findQuizDetails();

    List<Quiz> findByTitle(String title);

    @Query(value = "SELECT q.id AS quizzesid, q.title AS quizzestitle, q.description AS quizzesdescription, " +
            "q.time_create AS quizzestimecreate, q.quiz_time AS quiztime, q.quantity AS quantity, " +
            "q.passing_score AS passingscore, q.difficulty AS difficulty " +
            "FROM quizzes q JOIN users u ON q.created_by = u.id " +
            "WHERE q.created_by = :userId " +
            "ORDER BY q.time_create DESC",
            nativeQuery = true)
    List<QuizTeacherDTO> findTeacherQuizDetails(@Param("userId") Long userId);

    @Query(value = "SELECT q.id AS id, q.title AS title, q.quantity AS quantity FROM quizzes q", nativeQuery = true)
    List<QuizStudentDTO> findAllQuizzesWithDTO();

    @Query("SELECT new com.codegym.quizappbackendmodule6.model.dto.QuizNameDTO(q.title) FROM Quiz q")
    List<QuizNameDTO> findAllQuizNames();

    @Query(value = "SELECT c.name AS Category_Name, q.title AS Quiz_Title, COUNT(r.id) AS Number_Of_Participants " +
            "FROM categories c " +
            "JOIN quizzes q " +
            "ON c.id = q.category_id " +
            "LEFT JOIN results r " +
            "ON q.id = r.quiz_id GROUP BY c.name, q.title " +
            "ORDER BY c.name, Number_Of_Participants DESC;",
            nativeQuery = true)
    List<QuizDTO> getQuizzesByCategory();

    @Query("SELECT new com.codegym.quizappbackendmodule6.model.dto.QuizHotDTO(q.id, q.title, COUNT(r.id)) " +
            "FROM Quiz q LEFT JOIN Result r ON q.id = r.quiz.id AND r.status = :status " +
            "GROUP BY q.id, q.title " +
            "ORDER BY COUNT(r.id) DESC")
    List<QuizHotDTO> findTopQuizzesByResultCount(@Param("status") Boolean status);

    @Query("SELECT new com.codegym.quizappbackendmodule6.model.dto.QuizTeacherHistory(u.id, u.name, u.email, COUNT(r.id)) " +
            "FROM Result r JOIN r.user u " +
            "WHERE r.quiz.id = :quizId AND r.status = :status " +
            "GROUP BY u.id")
    List<QuizTeacherHistory> getHistoryUserByQuizId(@Param("quizId") Long quizId , @Param("status") Boolean status);
    @Query("SELECT q.id AS quizId, q.quizTime AS quizTime FROM Quiz q WHERE q.id = :quizId")
    QuizTimeDTO findQuizTimeById(@Param("quizId") Long quizId);
}