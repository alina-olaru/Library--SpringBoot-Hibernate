package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
@IdClass(QuizzUserId.class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizzUser  implements Serializable {

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"userQuizzes"}, allowSetters = true)
  //  @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_FOR_QUIZZ_ID"))
    private BookUser userForQuizz;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"quizzUsers"}, allowSetters = true)
 //   @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_QUIZZ_FOR_USER_ID"))
    private Quizz quizzForUser;

}
