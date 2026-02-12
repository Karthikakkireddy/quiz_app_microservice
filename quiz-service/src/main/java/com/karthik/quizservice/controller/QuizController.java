package com.karthik.quizservice.controller;



import com.karthik.quizservice.domain.Quiz;
import com.karthik.quizservice.dto.AnswersDTO;
import com.karthik.quizservice.dto.QuestionResponseDTO;
import com.karthik.quizservice.dto.QuizDTO;
import com.karthik.quizservice.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController
{

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                quizService.createQuiz(quizDTO.getCategory(), quizDTO.getNumQ(), quizDTO.getTitle())
        );
    }

    @GetMapping("/getQuestions/{id}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestions(@PathVariable int id)
    {
        return ResponseEntity.ok(quizService.getQuestions(id));
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> calculateScore(@PathVariable int id, @RequestBody List<AnswersDTO> answersDTO)
    {
        return ResponseEntity.ok(quizService.calculateScore(id, answersDTO));
    }
}
