package com.alina.mylibrary.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Table
@Entity
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
    private Blob complaintImage;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_COMPLAINT_ID"))
    private BookUser com;
}
