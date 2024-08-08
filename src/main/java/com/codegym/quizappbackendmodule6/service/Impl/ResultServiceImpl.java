//package com.codegym.quizappbackendmodule6.service.Impl;
//
//import com.codegym.quizappbackendmodule6.model.Quiz;
//import com.codegym.quizappbackendmodule6.model.Result;
//import com.codegym.quizappbackendmodule6.model.User;
//import com.codegym.quizappbackendmodule6.repository.ResultRepository;
//import com.codegym.quizappbackendmodule6.service.QuizService;
//import com.codegym.quizappbackendmodule6.service.ResultService;
//import com.codegym.quizappbackendmodule6.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class ResultServiceImpl implements ResultService {
//    private final ResultRepository resultRepository;
//    private final UserService userService;
//    private final QuizService quizService;
//
//    @Override
//    public Result startQuiz(Long userId, Long quizId) {
//        Result result = new Result();
//        User user = userService.findById(userId).get();
//        Quiz quiz = quizService.findById(quizId).get();
//        result.setUser(user);
//        result.setQuiz(quiz);
//        result.setStartTime(LocalDateTime.now());
//        return resultRepository.save(result);
//    }
//
//    @Override
//    public Result endQuiz(Long resultId) {
//        Result result = resultRepository.findById(resultId).orElseThrow(() -> new RuntimeException("Result not found"));
//        result.setFinishTime(LocalDateTime.now());
//        return resultRepository.save(result);
//    }
//}
