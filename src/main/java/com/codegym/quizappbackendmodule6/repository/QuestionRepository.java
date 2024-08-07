package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.DTO.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query(value = "SELECT q.id AS questionId, q.question_text AS questionText, c.name AS categoryName, qt.type_name AS typeName, q.time_create AS timeCreate " +
            "FROM questions q " +
            "JOIN categories c ON q.category_id = c.id " +
            "JOIN question_types qt ON q.question_type_id = qt.id" +
            "ORDER BY q.time_create DESC",
            nativeQuery = true)
    List<QuestionDTO> findAllQuestionDetails();

    @Query(value = "SELECT q.id AS questionId, q.question_text AS questionText, c.name AS categoryName, qt.type_name AS typeName, q.time_create AS timeCreate " +
            "FROM questions q " +
            "JOIN categories c ON q.category_id = c.id " +
            "JOIN question_types qt ON q.question_type_id = qt.id " +
            "WHERE (:categoryName IS NULL OR c.name LIKE %:categoryName%) " +
            "AND (:questionName IS NULL OR q.question_text LIKE %:questionName%)" +
            "ORDER BY q.time_create DESC",
            nativeQuery = true)
    List<QuestionDTO> findQuestionsByCategoryAndName(@Param("categoryName") String categoryName,
                                                     @Param("questionName") String questionName);

    @Query(value = "SELECT q.id AS questionId, q.question_text AS questionText, c.name AS categoryName, qt.type_name AS typeName, q.time_create AS timeCreate " +
            "FROM questions q " +
            "JOIN categories c ON q.category_id = c.id " +
            "JOIN question_types qt ON q.question_type_id = qt.id " +
            "JOIN quizzes qui ON qui.id = q.quiz_id " +
            "WHERE qui.created_by = :userId " +
            "ORDER BY q.time_create DESC",
            nativeQuery = true)
    List<QuestionDTO> findAllTeacherQuestionDetails(@Param("userId") Long userId);

}
