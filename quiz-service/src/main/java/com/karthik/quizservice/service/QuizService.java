package com.karthik.quizservice.service;


import com.karthik.quizservice.domain.Quiz;
import com.karthik.quizservice.dto.AnswersDTO;
import com.karthik.quizservice.dto.QuestionResponseDTO;
import com.karthik.quizservice.feign.QuizInterface;
import com.karthik.quizservice.repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuizService
{
    private final QuizRepo quizRepo;
//    private final QuestionRepo questionRepo;
    private final QuizInterface quizInterface;
    public Quiz createQuiz(String category, int numQ, String title)
    {
//        List<Questions> questionsList = questionRepo.findRandomQuestionsByCategory(category, numQ); // Calling question service

        List<Integer> questionsIdList = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);

        quiz.setQuestionIds(questionsIdList);

        return  quizRepo.save(quiz);

    }

    public List<QuestionResponseDTO> getQuestions(int id)
    {
        Quiz quiz = quizRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID NOT FOUND " + id));
        List<Integer> questionsList = quiz.getQuestionIds();

        List<QuestionResponseDTO>  questionResponseDTOS = quizInterface.getQuestionsFromId(questionsList).getBody();



        return questionResponseDTOS;

    }
    public Integer calculateScore(int id, List<AnswersDTO> answersDTO)
    {
        Integer result = quizInterface.getScore(answersDTO).getBody();

        return result;

    }

//    private QuestionResponseDTO mapToQuestionResponse(Questions questions)
//    {
//        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
//        questionResponseDTO.setQuestionTitle(questions.getQuestionTitle());
//        questionResponseDTO.setOption1(questions.getOption1());
//        questionResponseDTO.setOption2(questions.getOption2());
//        questionResponseDTO.setOption3(questions.getOption3());
//        questionResponseDTO.setOption4(questions.getOption4());
//
//        return questionResponseDTO;
//    }


}
