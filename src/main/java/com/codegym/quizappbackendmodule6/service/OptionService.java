package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Option;
import com.codegym.quizappbackendmodule6.model.dto.OptionStudentDTO;

import java.util.List;
import java.util.Optional;

public interface OptionService {
    Option saveOption(Option option, Long questionId);

    List<OptionStudentDTO> findOptionsByQuestionId(Long questionId);

    Optional<Option> findById(Long optionId);

    void saveOption(List<Option> options);

    void deleteById(Long optionId); // Add this method

    List<Option> findCorrectOptionsByQuestionId(Long id);
}
