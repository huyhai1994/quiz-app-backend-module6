package com.codegym.quizappbackendmodule6.model.dto;

import com.codegym.quizappbackendmodule6.model.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String questionText;
    private Set<OptionDTO> options;
}
