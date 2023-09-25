package com.moh.quizservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moh.quizservice.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
