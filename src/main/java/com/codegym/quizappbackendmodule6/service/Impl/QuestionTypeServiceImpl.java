package com.codegym.quizappbackendmodule6.service.Impl;


import com.codegym.quizappbackendmodule6.model.QuestionType;
import com.codegym.quizappbackendmodule6.repository.QuestionTypeRepository;
import com.codegym.quizappbackendmodule6.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionTypeServiceImpl implements QuestionTypeService {
    private final QuestionTypeRepository questionTypeRepository;


    @Override
    public Optional<QuestionType> findById(Long id) {
        return questionTypeRepository.findById(id);
    }

    @Override
    public List<QuestionType> findAll() {
        return questionTypeRepository.findAll();
    }
}
