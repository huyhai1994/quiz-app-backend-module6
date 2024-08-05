package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.repository.OptionRepository;
import com.codegym.quizappbackendmodule6.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
}
