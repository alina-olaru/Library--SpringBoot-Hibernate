package com.alina.mylibrary.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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


   // List<Book>

    public static Author get_copy(Author old_author)
    {
        Author new_author = new Author();
        new_author.setAuthorId(old_author.getAuthorId());
        new_author.setLastName(old_author.getLastName());
        new_author.setFirstName(old_author.getFirstName());
        return new_author;
    }

    @OneToMany(mappedBy = "authorId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BooksAuthors> bookAuthor;

}
