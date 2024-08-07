package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Option;
import com.codegym.quizappbackendmodule6.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option,Long> {
    List<Option> findByQuestion(Question question);
}
