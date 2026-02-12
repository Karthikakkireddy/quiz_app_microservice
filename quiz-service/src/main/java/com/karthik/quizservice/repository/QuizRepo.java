package com.karthik.quizservice.repository;


import com.karthik.quizservice.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer>
{

}
