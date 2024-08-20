package com.codegym.quizappbackendmodule6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "categories")
@JsonIgnoreProperties("createdBy")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tên không được để trống")
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Lob
    private String description;
}