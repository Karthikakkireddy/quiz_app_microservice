package com.karthik.quizservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuizDTO
{
    private String category;
    private int numQ;
    private String title;
}
