package com.karthik.questionservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswersDTO
{
    private Integer questionId;
    private String choosenAnswer;
}
