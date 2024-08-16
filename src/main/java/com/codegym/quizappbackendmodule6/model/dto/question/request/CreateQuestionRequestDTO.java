package com.codegym.quizappbackendmodule6.model.dto.question.request;

import com.codegym.quizappbackendmodule6.model.Category;
import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.QuestionType;
import com.codegym.quizappbackendmodule6.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionRequestDTO {
    @NotNull
    private String questionText;

    @NotNull
    private QuestionType questionType;

    @NotNull
    private Question.Difficulty difficulty;

    @NotNull
    private Category category;

    @NotNull
    private User createdBy;

    @NotEmpty
    private List<OptionDTO> options;

    @Data
    public static class OptionDTO {
        @NotNull
        private String optionText;

        @NotNull
        private Boolean isCorrect;
    }
}