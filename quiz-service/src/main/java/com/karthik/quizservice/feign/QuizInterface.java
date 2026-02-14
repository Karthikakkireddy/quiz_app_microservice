package com.karthik.quizservice.feign;

import com.karthik.quizservice.dto.AnswersDTO;
import com.karthik.quizservice.dto.QuestionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface
{
    // generate questions for quiz
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category ,
                                                             @RequestParam Integer numQ);
    // get/{questionId}

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
    // getScore
    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<AnswersDTO> answersDTOs);

}
