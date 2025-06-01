package com.microservices.quiz_app.dto;

import com.microservices.quiz_app.model.DifficultyLevel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDto {
    private String category;
    private DifficultyLevel difficultyLevel;
    private String questionTitle;
    private String rightAnswer;
    private String option1;
    private String option2;
    private String option3;
}
