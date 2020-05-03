package com.alina.mylibrary.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;


    @Column(columnDefinition = "NVARCHAR(MAX)")
    @NotNull
    private String text;


    @Column(length = 20)
    private String subject;


    @Column
    @Lob
    private byte[] complaintImageDb;

    @Transient
    private String complaintImage;

    @JsonIgnoreProperties(ignoreUnknown=true, value = {"userComplaints"}, allowSetters = true)
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_COMPLAINT_ID"))
    private BookUser com;
}
