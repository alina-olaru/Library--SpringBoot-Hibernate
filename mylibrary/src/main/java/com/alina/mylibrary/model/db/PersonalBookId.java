package com.alina.mylibrary.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalBookId  implements Serializable {
    private int user;
    private int book;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalBookId that = (PersonalBookId) o;
        return user == that.user &&
                book == that.book;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book);
    }

    public PersonalBookId(int user) {
        this.user = user;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }
}
