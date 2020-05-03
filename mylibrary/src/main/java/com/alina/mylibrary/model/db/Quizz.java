package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @ElementCollection
    private List<String> quizzAnswers = new ArrayList<String>();


    @NotNull
    @Column(length = 100)
    private String quizzCorrectAnswer;


    @Column

    private Date quizzStartDate;

    @Column

    private Date quizzEndDate;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"quizzez"}, allowSetters = true)
    @OneToMany(mappedBy = "quizzez",
    fetch = FetchType.EAGER)
    /**
     * Un quizz are unul/mai multe vouchere -->voucher = child , quizz=parent
     *
     *
     */
    private List<Voucher> vouchersGotByQuizz;
}
