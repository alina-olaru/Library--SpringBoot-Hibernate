package com.alina.mylibrary.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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



    @Column

    private Date quizzStartDate;

    @Column

    private Date quizzEndDate;

    public Date getQuizzStartDate() {
        return quizzStartDate;
    }

    public void setQuizzStartDate(Date quizzStartDate) {
        this.quizzStartDate = quizzStartDate;
    }

    public Date getQuizzEndDate() {
        return quizzEndDate;
    }

    public void setQuizzEndDate(Date quizzEndDate) {
        this.quizzEndDate = quizzEndDate;
    }

    public Quizz() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quizz quizz = (Quizz) o;
        return quizzId == quizz.quizzId &&
                numberOfQuestions == quizz.numberOfQuestions &&
                quizzQuestion.equals(quizz.quizzQuestion) &&
                quizzAnswers.equals(quizz.quizzAnswers) &&
                quizzCorrectAnswer.equals(quizz.quizzCorrectAnswer) &&
                quizzStartDate.equals(quizz.quizzStartDate) &&
                quizzEndDate.equals(quizz.quizzEndDate) &&
                vouchersGotByQuizz.equals(quizz.vouchersGotByQuizz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizzId, numberOfQuestions, quizzQuestion, quizzAnswers, quizzCorrectAnswer, quizzStartDate, quizzEndDate, vouchersGotByQuizz);
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

    public Set<Voucher> getVouchersGotByQuizz() {
        return vouchersGotByQuizz;
    }

    public void setVouchersGotByQuizz(Set<Voucher> vouchersGotByQuizz) {
        this.vouchersGotByQuizz = vouchersGotByQuizz;
    }

    @OneToMany(mappedBy = "quizzez")
    private Set<Voucher> vouchersGotByQuizz;
}
