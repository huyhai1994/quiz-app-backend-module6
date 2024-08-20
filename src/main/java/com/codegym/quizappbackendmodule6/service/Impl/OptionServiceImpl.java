package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.Option;
import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.QuestionType;
import com.codegym.quizappbackendmodule6.model.dto.OptionStudentDTO;
import com.codegym.quizappbackendmodule6.repository.OptionRepository;
import com.codegym.quizappbackendmodule6.service.OptionService;
import com.codegym.quizappbackendmodule6.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final QuestionService questionService;

    @Override
    public Option saveOption(Option option, Long questionId) {
        Question question = questionService.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));

        option.setQuestion(question);

        QuestionType questionType = question.getQuestionType();

        if (questionType.getId() == 1) {
            List<Option> existingOptions = optionRepository.findByQuestion(question);

            long correctOptionsCount = existingOptions.stream().filter(Option::getIsCorrect).count();

            if (correctOptionsCount >= 1 && option.getIsCorrect()) {
                throw new RuntimeException("Chỉ có duy nhất 1 đáp án đúng trong câu này");
            }
        } else if (questionType.getId() == 2) {
            // Additional logic for question type 2 if needed
        }

        return optionRepository.save(option);
    }

    @Override
    public List<OptionStudentDTO> findOptionsByQuestionId(Long questionId) {
        return optionRepository.findOptionsByQuestionId(questionId);
    }

    @Override
    public Optional<Option> findById(Long optionId) {
        return optionRepository.findById(optionId);
    }

    @Override
    public void saveOption(List<Option> options) {
        optionRepository.saveAll(options);
    }

    @Override
    public void deleteById(Long optionId) {
        optionRepository.deleteById(optionId);
    }

    @Override
    public List<Option> findCorrectOptionsByQuestionId(Long id) {
        return optionRepository.findCorrectOptionsByQuestionId(id);
    }
}
