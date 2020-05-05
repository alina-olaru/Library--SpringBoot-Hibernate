package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.db.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByBookTitle(String bookTitle);

    @Query("select distinct b,p  from Book b " +
            " join BooksCategories bc  "+
            "on b.bookId=bc.booksC.bookId  "+
            "join Category c  "+
            " on c.categoryId=bc.categories.categoryId  "+
            " join Publisher p   "+
            " on p.publisherId=b.publisher.publisherId  "+
            " join BooksAuthors ba  "+
            " on ba.bookId.bookId=b.bookId  "+
            "join Author a on a.authorId=ba.authorId.authorId  "+
            "where lower(bc.categories.categoryTitle)=lower(:title)" +
            " and lower(c.categoryTitle)=lower(:title)"
    )
    List<Book> getBooksByQuery(@Param("title") String title, Pageable pageable);
    //TODO vezi de ce nu merge

}


