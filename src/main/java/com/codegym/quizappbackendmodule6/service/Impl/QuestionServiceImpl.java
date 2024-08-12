package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.Category;
import com.codegym.quizappbackendmodule6.model.dto.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.QuestionType;
import com.codegym.quizappbackendmodule6.repository.QuestionRepository;
import com.codegym.quizappbackendmodule6.service.CategoryService;
import com.codegym.quizappbackendmodule6.service.QuestionService;
import com.codegym.quizappbackendmodule6.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final CategoryService categoryService;
    private final QuestionTypeService questionTypeService;

    @Override
    public List<QuestionDTO> findAllQuestionDetails() {
        return questionRepository.findAllQuestionDetails();
    }

    @Override
    public Question save(Question question) {
        Category category = categoryService.findById(question.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        question.setCategory(category);

        QuestionType questionType = questionTypeService.findById(question.getQuestionType().getId())
                .orElseThrow(() -> new RuntimeException("QuestionType not found"));
        question.setQuestionType(questionType);
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(Long questionId) {
        return questionRepository.findById(questionId);
    }

    @Override
    public void deleteById(Long id) {

        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
        }else {
            throw new RuntimeException("Question not found with id: " + id);
        }
    }

    @Override
    public List<QuestionDTO> findQuestionsByCategoryAndName(String searchTerm) {
        return questionRepository.findQuestionsBySearchTerm(searchTerm);
    }

    @Override
    public List<QuestionDTO> findAllTeacherQuestionDetails(Long userId) {
        return questionRepository.findAllTeacherQuestionDetails(userId);
    }

}
