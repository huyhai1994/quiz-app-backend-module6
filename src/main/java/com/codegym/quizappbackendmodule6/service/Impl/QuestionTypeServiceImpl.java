package com.codegym.quizappbackendmodule6.service.Impl;


import com.codegym.quizappbackendmodule6.repository.QuestionTypeRepository;
import com.codegym.quizappbackendmodule6.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionTypeServiceImpl implements QuestionTypeService {
    private final QuestionTypeRepository questionTypeRepository;
}
