package com.alina.mylibrary.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
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

    public Author() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, firstName, lastName);
    }

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
    private Set<BooksAuthors> bookAuthor;

}
