package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzRepository extends JpaRepository<Quizz, Integer> {
}
