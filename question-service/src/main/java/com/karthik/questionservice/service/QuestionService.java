package com.karthik.questionservice.service;


import com.karthik.questionservice.domain.Questions;
import com.karthik.questionservice.repository.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionService
{
    private final QuestionRepo questionRepo;

    public List<Questions> getAllQuestions()
    {
        return questionRepo.findAll();
    }

    public List<Questions> getQuestionsByCategory(String category)
    {
        return questionRepo.findByCategory(category);
    }

    public Questions createQuestion(Questions question)
    {
        return questionRepo.save(question);
    }

    public List<Integer> genereateQuestionsForQuiz(String category, Integer numQ)
    {
        List<Integer> questionsList = questionRepo.findRandomQuestionsByCategory(category, numQ); // Calling question service
        return questionsList;
    }
}
