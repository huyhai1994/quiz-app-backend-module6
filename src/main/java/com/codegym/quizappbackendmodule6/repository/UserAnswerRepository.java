package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Long> {
    long countByUserAndQuestion_QuizzesAndOption_IsCorrect(User user, Quiz quiz, boolean isCorrect);
}
