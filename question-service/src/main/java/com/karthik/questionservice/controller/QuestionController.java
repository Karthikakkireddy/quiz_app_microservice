package com.karthik.questionservice.controller;



import com.karthik.questionservice.domain.Questions;
import com.karthik.questionservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController
{

    private final QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions()
    {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category)
    {
        return ResponseEntity.ok(questionService.getQuestionsByCategory(category));
    }

    @PostMapping("/createQuestion")
    public ResponseEntity<Questions> createQuestion(@RequestBody Questions question)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(question));
    }

    // generate questions for quiz
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category ,
                                                             @RequestParam Integer numQ)
    {
        List<Integer> listOfQuestions = questionService.genereateQuestionsForQuiz(category, numQ);
        return ResponseEntity.ok(listOfQuestions);
    }

    // get/{questionId}

    // getScore

}
