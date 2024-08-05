package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.repository.UserAnswerRepository;
import com.codegym.quizappbackendmodule6.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAnswerServiceImpl implements UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
}
