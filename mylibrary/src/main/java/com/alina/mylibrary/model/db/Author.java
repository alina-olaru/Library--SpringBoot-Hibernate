package com.alina.mylibrary.model.db;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;


    @JsonIgnoreProperties(ignoreUnknown=true, value = {"authorId"}, allowSetters = true)
    @OneToMany(mappedBy = "authorId")
    private List<BooksAuthors> bookAuthor;

    @JsonIgnore
    @OneToMany(mappedBy = "author_voucher",
                fetch = FetchType.LAZY)
    private List<Voucher> author_vouchers;

    public static Author get_copy(Author old_author) {
        Author new_author = new Author();
        new_author.setAuthorId(old_author.getAuthorId());
        new_author.setLastName(old_author.getLastName());
        new_author.setFirstName(old_author.getFirstName());
        return new_author;
    }

}
