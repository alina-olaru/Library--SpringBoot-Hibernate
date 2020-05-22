package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailWhenInStoc {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mailmeId;


    @Column
    private Integer bookmailme;

    @Column
    @Email
    private String emailformail;

    @Override
    public String toString()
    {
        return "";
    }
}
