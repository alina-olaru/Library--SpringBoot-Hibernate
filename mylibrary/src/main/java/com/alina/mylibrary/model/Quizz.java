package com.alina.mylibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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


    @OneToMany(mappedBy = "quizzez",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    /**
     * Un quizz are unul/mai multe vouchere -->voucher = child , quizz=parent
     *
     *
     */
    private List<Voucher> vouchersGotByQuizz;
}
