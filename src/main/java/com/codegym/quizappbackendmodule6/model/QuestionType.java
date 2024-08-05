package com.codegym.quizappbackendmodule6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "question_types")
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name")
    @NotEmpty(message = "Kiểu dạng câu hỏi không được để trống , câu hỏi có nhiều đáp án hoặc 1 đáp án")
    private String typeName;

}
