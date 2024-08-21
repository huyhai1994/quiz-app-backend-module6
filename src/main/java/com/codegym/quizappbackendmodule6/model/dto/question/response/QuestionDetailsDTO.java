package com.codegym.quizappbackendmodule6.model.dto.question.response;

import com.codegym.quizappbackendmodule6.model.dto.OptionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDetailsDTO {
    private Long id;
    private String questionText;
    private Set<OptionDTO> options;
    private String typeName;
    private String difficulty;
    private String category;
    private String createdBy;
    private LocalDateTime timeCreate;
}