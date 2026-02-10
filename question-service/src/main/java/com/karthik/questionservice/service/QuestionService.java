package com.karthik.questionservice.service;


import com.karthik.questionservice.domain.Questions;
import com.karthik.questionservice.dto.AnswersDTO;
import com.karthik.questionservice.dto.QuestionResponseDTO;
import com.karthik.questionservice.repository.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public List<QuestionResponseDTO> getQuestionsFromIds(List<Integer> questionIds)
    {
        List<Questions> questionsList = questionRepo.findAllById(questionIds);

        List<QuestionResponseDTO>  questionResponseDTOList = questionsList.stream()
                .map(this::mapToQuestionResponse)
                .toList();

        return questionResponseDTOList;
    }

    public Integer getScore(List<AnswersDTO> answersDTOs)
    {

        List<Integer> ids = answersDTOs.stream()
                .map(AnswersDTO::getQuestionId)
                .toList();



        List<Questions> questionsList = questionRepo.findAllById(ids);


        Integer answer =0;
        Map<Integer, String > correctAnswer = new HashMap<>();

        for(Questions q : questionsList)
        {
            correctAnswer.put(q.getId(), q.getRightAnswer());
        }

        for(AnswersDTO answers : answersDTOs)
        {
            String correct = correctAnswer.get(answers.getQuestionId());

            if(correct != null && correct.equals(answers.getChoosenAnswer()))
            {
                answer++;
            }
        }
        return answer;
    }

    private QuestionResponseDTO mapToQuestionResponse(Questions questions)
    {
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        questionResponseDTO.setQuestionTitle(questions.getQuestionTitle());
        questionResponseDTO.setOption1(questions.getOption1());
        questionResponseDTO.setOption2(questions.getOption2());
        questionResponseDTO.setOption3(questions.getOption3());
        questionResponseDTO.setOption4(questions.getOption4());

        return questionResponseDTO;
    }


}
