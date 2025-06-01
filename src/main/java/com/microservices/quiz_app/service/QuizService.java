package com.microservices.quiz_app.service;

import com.microservices.quiz_app.model.QuestionDto;
import com.microservices.quiz_app.model.Questions;
import com.microservices.quiz_app.model.Quiz;
import com.microservices.quiz_app.model.Response;
import com.microservices.quiz_app.repository.QuestionRepository;
import com.microservices.quiz_app.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    private static final int NO_OF_QUESTIONS=4;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String title,String category){
        Quiz quiz=new Quiz();
        quiz.setTitle(title);

        List<Questions> questionsList=questionRepository.findByCategoryAndNoOfQuestions(category,NO_OF_QUESTIONS);
        quiz.setQuestions(questionsList);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDto>> getQuiz(String title){
        Optional<Quiz> quiz=quizRepository.findByTitle(title);
        if(quiz.isPresent()){
           List<Questions> questionsList=quiz.get().getQuestions();
            List<QuestionDto> questionDtos = questionsList.stream()
                    .map(q -> new QuestionDto(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3()))
                    .toList();

            return ResponseEntity.ok(questionDtos);
        }
        else {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> submitQuizResponse(Integer quizId, List<Response> quizResponses){
        Optional<Quiz> quiz=quizRepository.findById(quizId);
        int score=0;
        if(quiz.isPresent()){
            List<Questions> questionsList=quiz.get().getQuestions();
            HashMap<Integer,String> answerKey=new HashMap<>();
            questionsList.forEach(questions -> {
                answerKey.put(questions.getId(),questions.getRightAnswer());
            });

            for(Response quizResponse:quizResponses){
                String userResponse=quizResponse.getResponseAnswer();
                String rightAnswer=answerKey.get(quizResponse.getQuestionId());
                if(userResponse.equals(rightAnswer)){
                    score++;
                }
            }

        }
        String result="Your score is "+score;
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
