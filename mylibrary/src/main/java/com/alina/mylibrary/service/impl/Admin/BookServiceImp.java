package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.dao.Interfaces.Admin.BooksAuthorsDao;
import com.alina.mylibrary.dao.Interfaces.Admin.BooksCategoriesDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksCategories;
import com.alina.mylibrary.service.Interfaces.Admin.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BooksAuthorsDao booksAuthorsDao;

    @Autowired
    private BooksCategoriesDao booksCategoriesDao;

    @Override
    public Book addBook(Book book) {
        /**
         *
         *           we're building the app with the idea that one book can be found
         *          in the pages more time(same title,author...but different year,cover,..something)
         *         all the books with same title with whom you want to introduce.
         *        the book i allready inserted,you can only edit it for increasing the numbers of volumes

         *
         */



        List<Book> books=this.bookDao.getBooks();
        for(Book bookWithSameTitle:books){
            if(bookWithSameTitle.equals(book)){

                return null;
            }
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Book deepCopy = objectMapper
                    .readValue(objectMapper.writeValueAsString(book), Book.class);

            //you are able to insert the book
            book.getBooksCategories().clear();
            book.getBookAuthor().clear();
            if(book.getLastPrice() ==null){
                book.setLastPrice((double)book.getBookPrice());

            }
            this.bookDao.addBook(book);

            deepCopy.getBookAuthor().forEach(elem -> {
                elem.setBookId(book);
                this.booksAuthorsDao.addBooksAuthors(elem);
            });
            deepCopy.getBooksCategories().forEach(elem -> {
                elem.setBooksC(book);
                this.booksCategoriesDao.addBooksCategories(elem);
            });
            book.getBookAuthor().addAll(deepCopy.getBookAuthor());
            book.getBooksCategories().addAll(deepCopy.getBooksCategories());
            return book;
        } catch (Exception ex){
            return null;
        }

    }

    @Override
    public Book updateBook(Book book) {
     if(book!=null) {
         Book bookBeforeUpdate=this.bookDao.getBookbyId(book.getBookId());
         double last_price=bookBeforeUpdate.getBookPrice();
         book.setLastPrice(last_price);
         book.getBookAuthor().forEach(elem -> elem.setBookId(book));
         book.getBooksCategories().forEach(elem -> elem.setBooksC(book));
         this.bookDao.updateBook(book);
         return book;
     }
     return null;
    }

    @Override
    @Transactional
    public boolean deleteBook(int bookId) {
        var book = this.bookDao.getBookbyId(bookId);
        if(book.getBooksCategories().size() > 0){
            book.getBooksCategories().forEach(booksCategories -> {
                this.booksCategoriesDao.deleteBooksCategories(booksCategories);
            });
        }
        if(book.getBookAuthor().size() > 0){
            book.getBookAuthor().forEach(booksAuthors -> {
                this.booksAuthorsDao.delteBooksAuthors(booksAuthors);
            });
        }
        this.bookDao.deleteBook(bookId);
        return true;
    }

    @Override
    public List<Book> getBook() {
       return this.bookDao.getBooks();
    }

    @Override
    public Book getBookById(int id) throws DBExceptions {
        Book response=null;
        try{
            response=this.bookDao.getBookbyId(id);
        }catch (Exception e){
            throw new DBExceptions(e.getMessage(), 404, this.getClass().getName(), "book obj", "get");
        }

        return response;
    }

    @Override
    public List<Book> getBookByCategory(String category) throws DBExceptions {
        if(category.equals(null)){
            throw new DBExceptions("Obiectul trimis este gol", 400, this.getClass().getName(), "Category obj", "get");

        }
     List<Book> books=this.bookDao.getBooks();
     List<Book> result=new ArrayList<>();
     for(Book b:books){
        List<BooksCategories> bc= b.getBooksCategories();
        for(BooksCategories y:bc){
            if(y.getCategories().getCategoryTitle().toLowerCase().equals(category)){
                result.add(b);
            }
        }
         //for( com.alina.mylibrary.model.db.Category c:b){


        // }
     }
     if(result.size()!=0) {
         return result;
     }
     else{
         throw new DBExceptions("Nu exista carti cu categoria data in baza de date", 404, this.getClass().getName(), "Category obj", "get");

     }
    }

    @Override
    public List<Book> getBooksByQuery(String title, int count) throws DBExceptions ,Exception{


        List<Book> response=new ArrayList<>();
        try {
            response = this.bookDao.getBooksByQuery(title, count);
            if(response.size()>0){
                return response;
            }
        }catch (DaoException e){
            throw new DBExceptions(e.getMessage(), 404, this.getClass().getName(), "Book obj", "get");

        }catch (HttpMessageConversionException e){
            throw new DBExceptions(e.getMessage() + " Problema de mapare "+e.getLocalizedMessage(), 404, this.getClass().getName(), "Book obj", "get");
        }
        catch (Exception e){
            throw new DBExceptions(e.getMessage(), 404, this.getClass().getName(), "Book obj", "get");

        }
        if(response.size()==0){
            throw new DBExceptions("Service issues(BookService)", 400, this.getClass().getName(), "Book obj", "get");

        }
        return response;
        }


    }




