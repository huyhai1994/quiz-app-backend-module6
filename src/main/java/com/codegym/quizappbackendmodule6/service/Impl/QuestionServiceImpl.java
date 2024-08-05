package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.repository.QuestionRepository;
import com.codegym.quizappbackendmodule6.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
}
