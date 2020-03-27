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

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Blob getComplaintImage() {
        return complaintImage;
    }

    public void setComplaintImage(Blob complaintImage) {
        this.complaintImage = complaintImage;
    }

    public BookUser getCom() {
        return com;
    }

    public void setCom(BookUser com) {
        this.com = com;
    }

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="FK_USER_COMPLAINT_ID"))
    private BookUser com;
}
