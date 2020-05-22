package com.alina.mylibrary.service.Search;

import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import com.alina.mylibrary.model.db.BooksCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class searchService {

    @Autowired
    BookDao bookDao;


  public HashSet<Book> search(String keyword){
      /**
       * Aflu cuvintele cheie ->split dupa spatiu si memorez cuvintele cu lungime >=3 (fara cele de legatura)
       *
       *
       */

      List<String> keyWords = new ArrayList<>();
      keyWords.addAll(Arrays.asList(keyword.split("\\s+")));
      List<Book> books = this.bookDao.getBooks();
      List<Book> response = new ArrayList<>();
      for(Book book : books) {
          if (book.getBookTitle().toLowerCase() == keyword.toLowerCase()) {
              response.add(book);

          }
          for (String word : keyWords) {
              if (book.getBookTitle().toLowerCase().contains(word.toLowerCase())){
                  response.add(book);
              }
          }

          for(BooksAuthors ba : book.getBookAuthor()){
              if(((ba.getAuthorId().getFirstName().toLowerCase().concat(ba.getAuthorId().getLastName().toLowerCase()))==keyword.toLowerCase())||
                      ((ba.getAuthorId().getLastName().toLowerCase().concat(ba.getAuthorId().getFirstName().toLowerCase()))==keyword.toLowerCase())){
                  response.add(book);
              }
              for (String word : keyWords) {
                  if((ba.getAuthorId().getLastName().toLowerCase().contains(word.toLowerCase()))||(ba.getAuthorId().getFirstName().toLowerCase().contains(word.toLowerCase()))){
                      response.add(book);
                  }
              }
          }

          for(BooksCategories bc : book.getBooksCategories()){
              if(bc.getCategories().getCategoryTitle().toLowerCase()==keyword.toLowerCase()){
                  response.add(book);

              }
              for (String word : keyWords) {
                  if(bc.getCategories().getCategoryTitle().toLowerCase().contains(word.toLowerCase())){
                      response.add(book);
                  }
              }
          }
      }



      return new HashSet<Book>(response);
  }
}
