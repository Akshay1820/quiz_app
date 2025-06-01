package com.microservices.quiz_app.model;

public class Response {
    private Integer questionId;
    private String responseAnswer;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getResponseAnswer() {
        return responseAnswer;
    }

    public void setResponseAnswer(String responseAnswer) {
        this.responseAnswer = responseAnswer;
    }
}
