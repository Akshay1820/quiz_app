package com.microservices.quiz_app.repository;

import com.microservices.quiz_app.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {

    List<Questions> getQuestionsByCategory(String categoryName);


    @Query(value = """
            Select * from questions q where q.category=:category order by RAND() limit :numQ
            """,nativeQuery = true)
    List<Questions> findByCategoryAndNoOfQuestions(String category,int numQ);
}
