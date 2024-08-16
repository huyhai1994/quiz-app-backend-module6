package com.codegym.quizappbackendmodule6.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateQuizRequestDto {
    @NotEmpty(message = "Tiêu đề không được để trống")
    private String title;

    private String description;

    @NotNull(message = "Thời gian làm bài không được để trống")
    private Long quizTime;

    @NotNull(message = "Số lượng câu hỏi không được để trống")
    private Integer quantity;

    @NotNull(message = "Điểm đạt không được để trống")
    private Integer passingScore;

    private Set<Long> questionIds;
}
