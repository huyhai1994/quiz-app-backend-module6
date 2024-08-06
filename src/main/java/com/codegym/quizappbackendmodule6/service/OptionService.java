package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Option;

public interface OptionService {
    Option saveOption(Option option, Long questionId);
}
