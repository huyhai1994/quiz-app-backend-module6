package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.dto.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query(value = "SELECT q.id AS questionId, q.question_text AS questionText, c.name AS categoryName, qt.type_name AS typeName, q.time_create AS timeCreate " +
            "FROM questions q " +
            "JOIN categories c ON q.category_id = c.id " +
            "JOIN question_types qt ON q.question_type_id = qt.id",
            nativeQuery = true)
    List<QuestionDTO> findAllQuestionDetails();
}
