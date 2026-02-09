package com.karthik.questionservice.repository;


import com.karthik.questionservice.domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);


    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ",
            nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(String category, int numQ);
}
