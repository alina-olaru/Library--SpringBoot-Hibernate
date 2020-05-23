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

    @Column
    @NotNull
    private Double bonus;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"quizzForUser"}, allowSetters = true)
    @OneToMany(mappedBy = "quizzForUser",
            fetch = FetchType.LAZY)
    private List<QuizzUser> quizzUsers;

}
