package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.dto.AddQuestionIntoQuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuestionStudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT q.id AS questionid, q.question_text AS questiontext, c.name AS categoryname, qt.type_name AS typename, q.time_create AS timecreate " + "FROM questions q " + "JOIN categories c ON q.category_id = c.id " + "JOIN question_types qt ON q.question_type_id = qt.id " + "ORDER BY q.time_create DESC", nativeQuery = true)
    List<QuestionDTO> findAllQuestionDetails();

    @Query(value = "SELECT q.id AS questionid, q.question_text AS questiontext, c.name AS categoryname, qt.type_name AS typename, q.time_create AS timecreate " + "FROM questions q " + "JOIN categories c ON q.category_id = c.id " + "JOIN question_types qt ON q.question_type_id = qt.id " + "WHERE (:searchterm IS NULL OR c.name LIKE %:searchTerm% OR q.question_text LIKE %:searchTerm%) " + "ORDER BY q.time_create DESC", nativeQuery = true)
    List<QuestionDTO> findQuestionsBySearchTerm(@Param("searchTerm") String searchTerm);

    @Query(value = "SELECT q.id AS questionid, " + "q.question_text AS questiontext," + " c.name " + "AS categoryname, " + "qt.type_name AS typename," + " q.time_create AS timecreate " + "FROM questions q " + "JOIN categories c ON q.category_id = c.id " + "JOIN question_types qt ON q.question_type_id = qt.id " + "WHERE q.created_by = :userid " + "ORDER BY q.time_create DESC", nativeQuery = true)
    List<QuestionDTO> findAllTeacherQuestionDetails(@Param("userid") Long userid);

    @Query(value = "SELECT q.id AS questionid, q.question_text AS questiontext, qt.type_name AS questiontypename " + "FROM questions q " + "JOIN question_types qt ON q.question_type_id = qt.id " + "JOIN categories c ON q.category_id = c.id " + "JOIN users u ON u.id = c.created_by " + "WHERE c.name = :categoryname AND u.id = :userid", nativeQuery = true)
    List<AddQuestionIntoQuizDTO> addQuestionsByCategoryNameAndUserId(@Param("categoryname") String categoryname, @Param("userid") Long userid);

    List<Question> findAllById(Iterable<Long> ids);

    @Query(value = "SELECT q.id AS id, q.question_text AS questiontext " + "FROM questions q " + "JOIN quiz_questions qq ON q.id = qq.question_id " + "WHERE qq.quiz_id = :quizid", nativeQuery = true)
    List<QuestionStudentDTO> findQuestionDTOsByQuizId(@Param("quizid") Long quizid);

    @Query("SELECT q FROM Question q JOIN q.quizzes quiz WHERE quiz.id = :quizId")
    List<Question> findAllByQuizId(@Param("quizId") Long quizId);

    @Query("SELECT CASE WHEN COUNT(qq) > 0 THEN true ELSE false END FROM Quiz q JOIN q.questions qq WHERE qq.id = :questionId")
    boolean isQuestionInAnyQuiz(@Param("questionId") Long questionId);
}