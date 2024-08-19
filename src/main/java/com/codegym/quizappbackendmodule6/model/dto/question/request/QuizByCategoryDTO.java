package com.codegym.quizappbackendmodule6.model.dto.question.request;

import com.codegym.quizappbackendmodule6.model.QuizCategory;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizByCategoryDTO {
    private Long id;
    private String title;
    private String description;
    private QuizCategory quizCategory;

}
