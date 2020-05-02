package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Book;
import com.alina.mylibrary.model.BookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByBookTitle(String bookTitle);

//    @Query("select distinct b,p  from Book b " +
//            " join BooksCategories bc \n"+
//            "on b.bookId=bc.booksC.bookId \n"+
//            "join Category c \n"+
//            " on c.categoryId=bc.categories.categoryId \n"+
//            " join Publisher p \n "+
//            " on p.publisherId=b.publisher.publisherId \n"+
//            " join BooksAuthors ba \n"+
//            " on ba.bookId.bookId=b.bookId \n"+
//            "join Author a on a.authorId=ba.authorId.authorId \n"+
//            "where lower(bc.categories.categoryTitle)=lower(:title)" +
//            " and lower(c.categoryTitle)=lower(:title)"
//    )
//    List<Book> getBooksByQuery(@Param("title") String title);
    //TODO vezi de ce nu merge

}


