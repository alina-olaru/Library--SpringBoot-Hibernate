package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.BookApi;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.service.Interfaces.Admin.BookService;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import com.alina.mylibrary.service.Interfaces.Guess.MailMeService;
import com.alina.mylibrary.service.impl.Admin.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Component
public class BookApiImp implements BookApi {

    /**
     *
     *
     *<p>Rest api for books(only CRUD operations ) from admin interfaces</p>
     * @see <a href="http://localhost:4200/admin/carti"></a>
     * since 1.0.0
     */

    @Autowired
    private BookService bookService;

    @Autowired
    private MailMeService mailMeService;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    BookUserService bookUserService;


    @Override

    public ApiResponse<Book> insertBook(Book book) {
       if(book!=null){

           Book response = this.bookService.addBook(book);
           return new ApiResponse<Book>(ApiResponseType.SUCCESS,response);
       }

       return new ApiResponse<Book>(ApiResponseType.ERROR,null,"Cartea nu poate fi adaugata");
    }

    @Override
    public ApiResponse<List<Book>> getBooks()
    {
        List<Book> response=this.bookService.getBook();
        return new ApiResponse<List<Book>>(ApiResponseType.SUCCESS,response);

    }

    @Override
    public ApiResponse<Book> updateBook(Book book) {
       if(book==null){
           return new ApiResponse<Book>(ApiResponseType.ERROR,null,"Cartea nu poate fi editata");
       }
       Book response=this.bookService.updateBook(book);


       List<String> mails = this.mailMeService.sendMails(book.getBookId());
       if(mails.size()>0){
           SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
           //TODO ADD HTML IN MAIL
           simpleMailMessage.setFrom("olarualina01@gmail.com");
           simpleMailMessage.setSubject("Newsletter");
           simpleMailMessage.setText("Avem produsul din nou in stoc!Viziteaza-ne pagina!");
           for(String m:mails) {
               simpleMailMessage.setTo(m);
               emailSenderService.sendEmail(simpleMailMessage);

           }
       }

       return new ApiResponse<Book>(ApiResponseType.SUCCESS,response);
    }

    @Override
    public ApiResponse<Boolean> deleteBook(int id) {
      boolean response= this.bookService.deleteBook(id);
      return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
    }


}
