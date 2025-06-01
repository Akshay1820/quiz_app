package com.microservices.quiz_app.controller;

import com.microservices.quiz_app.model.QuestionDto;
import com.microservices.quiz_app.model.Response;
import com.microservices.quiz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam(name = "title")String title,
                                             @RequestParam(name = "category")String category){
        return quizService.createQuiz(title,category);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<QuestionDto>> getQuizByTitle(@PathVariable String title){
        return quizService.getQuiz(title);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<String> submitQuiz(@PathVariable Integer quizId,
                                             @RequestBody List<Response> quizResponses){
        return quizService.submitQuizResponse(quizId,quizResponses);
    }
}
