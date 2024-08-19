package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Option;
import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.dto.OptionStudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestion(Question question);

    @Query(value = "SELECT o.id AS id, o.option_text AS optiontext FROM options o WHERE o.question_id = :questionId", nativeQuery = true)
    List<OptionStudentDTO> findOptionsByQuestionId(@Param("questionId") Long questionId);

    @Query("SELECT o FROM Option o WHERE o.question.id = :questionId AND o.isCorrect = true")
    List<Option> findCorrectOptionsByQuestionId(Long questionId);
}
