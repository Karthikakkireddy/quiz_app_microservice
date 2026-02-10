package com.karthik.questionservice.repository;


import com.karthik.questionservice.domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);


    @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ",
            nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
