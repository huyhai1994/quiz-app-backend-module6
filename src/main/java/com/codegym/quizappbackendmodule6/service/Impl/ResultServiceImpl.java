package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.repository.ResultRepository;
import com.codegym.quizappbackendmodule6.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
}
