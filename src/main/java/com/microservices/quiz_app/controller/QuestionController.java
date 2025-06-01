package com.microservices.quiz_app.controller;

import com.microservices.quiz_app.model.Questions;
import com.microservices.quiz_app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @RequestMapping("/getQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @RequestMapping("/category/{categoryName}")
    public List<Questions> getQuestionsByCategory(@PathVariable String categoryName){
        return questionService.getByCategory(categoryName);
    }

    @PostMapping()
    public ResponseEntity<String> addQuestions(@RequestBody Questions questions){
        return questionService.addNewQuestions(questions);
    }
}
