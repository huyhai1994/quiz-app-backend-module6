package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
}