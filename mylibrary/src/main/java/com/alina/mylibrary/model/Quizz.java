package com.alina.mylibrary.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table
public class Quizz {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizzId;


    @Column()
    private int numberOfQuestions;

    @NotNull
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String quizzQuestion;


    @NotNull
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String quizzAnswers;


    @NotNull
    @Column(length = 100)
    private String quizzCorrectAnswer;

    public Quizz() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quizz quizz = (Quizz) o;
        return quizzId == quizz.quizzId &&
                numberOfQuestions == quizz.numberOfQuestions &&
                Objects.equals(quizzQuestion, quizz.quizzQuestion) &&
                Objects.equals(quizzAnswers, quizz.quizzAnswers) &&
                Objects.equals(quizzCorrectAnswer, quizz.quizzCorrectAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizzId, numberOfQuestions, quizzQuestion, quizzAnswers, quizzCorrectAnswer);
    }

    public int getQuizzId() {
        return quizzId;
    }

    public void setQuizzId(int quizzId) {
        this.quizzId = quizzId;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getQuizzQuestion() {
        return quizzQuestion;
    }

    public void setQuizzQuestion(String quizzQuestion) {
        this.quizzQuestion = quizzQuestion;
    }

    public String getQuizzAnswers() {
        return quizzAnswers;
    }

    public void setQuizzAnswers(String quizzAnswers) {
        this.quizzAnswers = quizzAnswers;
    }

    public String getQuizzCorrectAnswer() {
        return quizzCorrectAnswer;
    }

    public void setQuizzCorrectAnswer(String quizzCorrectAnswer) {
        this.quizzCorrectAnswer = quizzCorrectAnswer;
    }
}
