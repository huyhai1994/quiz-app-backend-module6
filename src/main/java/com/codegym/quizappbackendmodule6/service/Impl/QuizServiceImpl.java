package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.DTO.QuizDTO;
import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.repository.QuizRepository;
import com.codegym.quizappbackendmodule6.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Override
    public List<QuizDTO> findQuizDetails() {
        return quizRepository.findQuizDetails();
    }

    @Override
    public List<Quiz> getQuizByCategory(String title) {
        return quizRepository.findByTitle(title);
    }

    @Override
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
        } else {
            throw new RuntimeException("Quiz không tồn tại với ID: " + id);
        }
    }

    @Override
    public Quiz updateQuiz( Quiz quiz, Long id) {
        if (!quizRepository.existsById(id)) {
            throw new RuntimeException("Quiz không tồn tại với ID: " + id);
        }
        quiz.setId(id);
        return quizRepository.save(quiz);
    }
}
