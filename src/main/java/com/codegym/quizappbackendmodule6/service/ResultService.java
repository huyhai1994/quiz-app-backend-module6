package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Result;

public interface ResultService {
    Result startQuiz(Long userId, Long quizId);
    Result endQuiz(Long resultId);
}
