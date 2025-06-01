package com.microservices.quiz_app.service;


import com.microservices.quiz_app.model.Questions;
import com.microservices.quiz_app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;



    public List<Questions> getAllQuestions(){
        return questionRepository.findAll();
    }

    public List<Questions> getByCategory(String categoryName){
        return questionRepository.getQuestionsByCategory(categoryName);
    }

    public ResponseEntity<String> addNewQuestions(Questions questions){
        questionRepository.save(questions);
        return new ResponseEntity<>("New question added...", HttpStatus.CREATED);
    }

}

