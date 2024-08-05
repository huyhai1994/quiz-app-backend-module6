package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {

    List<Quiz> findByTitle(String title);

}
