package com.alina.mylibrary.service.Search;

import com.alina.mylibrary.dao.Interfaces.Admin.BookDao;
import com.alina.mylibrary.exception.ServiceException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksAuthors;
import com.alina.mylibrary.model.db.BooksCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class searchService {

    @Autowired
    BookDao bookDao;


    public HashSet<Book> search(String keyword) {
        /**
         * Aflu cuvintele cheie ->split dupa spatiu si memorez cuvintele cu lungime >=3 (fara cele de legatura)
         *
         *
         */


        if((keyword.toLowerCase().equals("empty")||(keyword.toLowerCase()=="empty"))){
            return new HashSet<>(this.bookDao.getBooks());

        }

        List<String> keyWords = new ArrayList<>();
        keyWords.addAll(Arrays.asList(keyword.split("\\s+")));
        List<Book> books = this.bookDao.getBooks();
        List<Book> response = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookTitle().toLowerCase() == keyword.toLowerCase()) {
                response.add(book);

            }

            if (book.getPublisher().getPublisherTitle().toLowerCase().contains(keyword.toLowerCase())) {
                response.add(book);
            }
            for (String word : keyWords) {
                if (book.getBookTitle().toLowerCase().contains(word.toLowerCase())) {
                    response.add(book);
                }
            }

            for (BooksAuthors ba : book.getBookAuthor()) {
                if (((ba.getAuthorId().getFirstName().toLowerCase().concat(ba.getAuthorId().getLastName().toLowerCase())) == keyword.toLowerCase()) ||
                        ((ba.getAuthorId().getLastName().toLowerCase().concat(ba.getAuthorId().getFirstName().toLowerCase())) == keyword.toLowerCase())) {
                    response.add(book);
                }
                if (((ba.getAuthorId().getLastName().toLowerCase()==keyword.toLowerCase()) || (ba.getAuthorId().getFirstName().toLowerCase()==keyword.toLowerCase())) ||
                        (((ba.getAuthorId().getLastName().toLowerCase()==keyword.toLowerCase()) && (ba.getAuthorId().getFirstName().toLowerCase()==keyword.toLowerCase()))) ||
                        ((ba.getAuthorId().getLastName().toLowerCase().equals(keyword.toLowerCase())) || (ba.getAuthorId().getFirstName().toLowerCase().equals(keyword.toLowerCase()))) ||
                        ((ba.getAuthorId().getLastName().toLowerCase().equals(keyword.toLowerCase())) && (ba.getAuthorId().getFirstName().toLowerCase().equals(keyword.toLowerCase())))){
                    response.add(book);
                }
                for (String word : keyWords) {
                    if ((ba.getAuthorId().getLastName().toLowerCase().contains(word.toLowerCase())) || (ba.getAuthorId().getFirstName().toLowerCase().contains(word.toLowerCase()))) {
                        response.add(book);
                    }

                }
            }

            for (BooksCategories bc : book.getBooksCategories()) {
                if (bc.getCategories().getCategoryTitle().toLowerCase() == keyword.toLowerCase()) {
                    response.add(book);

                }
                for (String word : keyWords) {
                    if (bc.getCategories().getCategoryTitle().toLowerCase().contains(word.toLowerCase())) {
                        response.add(book);
                    }
                }
            }
        }


        return new HashSet<Book>(response);
    }

    public HashSet<Book> filter(Optional<Integer> disponibility, Optional<Integer> minPrice,
                                Optional<Integer> maxPrice, Optional<Integer> ratingMin,
                                Optional<List<Integer>> authorsIds, Optional<List<Integer>> categoriesIds,
                                Optional<List<Integer>> publishersIds) throws ServiceException {

        List<Book> books = new ArrayList<>();
        try {
            books = this.bookDao.getBooks();

        } catch (Exception ex) {
            throw new ServiceException();
        }


/**
 * salvez in partialResponse cartile ce respecta conditiile de reuniune ->autori,categorii,edituri
 */


        List<Book> partialResponse = new ArrayList<>();

        if (authorsIds.isPresent()) {
            List<Integer> autPref = authorsIds.get();
            for (Book book : books) {
                for (BooksAuthors ba : book.getBookAuthor()) {
                    if (autPref.contains(ba.getAuthorId())) {
                        partialResponse.add(book);
                    }
                }
            }
        }

        if (categoriesIds.isPresent()) {
            List<Integer> catPref = categoriesIds.get();
            for (Book book : partialResponse) {
                for (BooksCategories bc : book.getBooksCategories()) {
                    if (!catPref.contains(bc.getCategories().getCategoryId())) {
                        partialResponse.remove(book);
                    }
                }
            }
        }

        if (publishersIds.isPresent()) {
            List<Integer> pubPref = publishersIds.get();
            for (Book book : partialResponse) {
                if (!pubPref.contains(book.getPublisher().getPublisherId())) {
                    partialResponse.remove(book);
                }

            }
        }

        if (authorsIds.isEmpty() && categoriesIds.isEmpty() && publishersIds.isEmpty()) {
            partialResponse = this.bookDao.getBooks();
        }

/**
 * iterez prin partialResponse si salvez doar ce respecta conditiile de excludere ->pret,disponiblitate,rating
 */

/**
 * in response salvez raspunsul final , la fiecare filtrare sterg din raspuns cartile care nu respecta
 * regulile
 */
        List<Book> response = new ArrayList<>();

        if (disponibility.isPresent()) {
            for (Book book : partialResponse) {
                Integer disp = disponibility.get();
                switch (disp) {
                    case 0: {
                        if (book.getNumberOfBoooks() == 0) {
                            response.add(book);
                        }
                        break;
                    }
                    case 1: {
                        if (book.getNumberOfBoooks() > 0) {
                            response.add(book);
                        }
                        break;
                    }
                    case 2: {
                        if ((book.getNumberOfBoooks() > 0) && (book.getLastPrice() > book.getBookPrice())) {
                            response.add(book);
                        }
                        break;
                    }
                }
            }

        }

        if (minPrice.isPresent() && maxPrice.isPresent()) {
            for (Book book : response) {
                Integer minP = minPrice.get();
                Integer maxP = maxPrice.get();
                if ((book.getBookPrice() < minP) || (book.getBookPrice() > maxP)) {
                    response.remove(book);
                }
            }
        }

        if (ratingMin.isPresent()) {
            Integer ratingMinim = ratingMin.get();
            for (Book book : response) {
                if (book.getBookRating() < ratingMinim) {
                    response.remove(book);
                }
            }
        }

        return new HashSet<Book>(response);
    }

    public HashSet<Book> filterWithTitle(Optional<Integer> disponibility, Optional<Integer> minPrice,
                                         Optional<Integer> maxPrice, Optional<Integer> ratingMin,
                                         Optional<List<Integer>> authorsIds, Optional<List<Integer>> categoriesIds,
                                         Optional<List<Integer>> publishersIds, String quey) throws ServiceException,DBExceptions {




/**
 * salvez in partialResponse cartile ce respecta conditiile de reuniune ->autori,categorii,edituri
 */
        HashSet<Book> books=new HashSet<>();
     //   if((quey.toLowerCase().equals("empty")||(quey.toLowerCase()=="empty"))){



     //   }
   //    else{
            books = this.search(quey);
    //    }

        List<Book> partialResponse = new ArrayList<>();

        if (authorsIds.isPresent()) {
            List<Integer> autPref = authorsIds.get();
            for (Book book : books) {
                for (BooksAuthors ba : book.getBookAuthor()) {
                    if (autPref.contains(ba.getAuthorId().getAuthorId())) {
                        partialResponse.add(book);
                    }
                }
            }
        }

        if (categoriesIds.isPresent()) {
            List<Integer> catPref = categoriesIds.get();
            for (Book book : partialResponse) {
                for (BooksCategories bc : book.getBooksCategories()) {
                    if (catPref.contains(bc.getCategories().getCategoryId())) {
                        partialResponse.add(book);
                    }
                }
            }
        }

        if (publishersIds.isPresent()) {
            List<Integer> pubPref = publishersIds.get();
            for (Book book : partialResponse) {
                if (pubPref.contains(book.getPublisher().getPublisherId())) {
                    partialResponse.add(book);
                }

            }
        }

        if (authorsIds.isEmpty() && categoriesIds.isEmpty() && publishersIds.isEmpty()) {
            partialResponse = this.bookDao.getBooks();
        }

/**
 * iterez prin partialResponse si salvez doar ce respecta conditiile de excludere ->pret,disponiblitate,rating
 */

/**
 * in response salvez raspunsul final , la fiecare filtrare sterg din raspuns cartile care nu respecta
 * regulile
 */
        List<Book> response = new ArrayList<>();

        if (disponibility.isPresent()) {
            for (Book book : partialResponse) {
                Integer disp = disponibility.get();
                switch (disp) {
                    case 0: {
                        if (book.getNumberOfBoooks() == 0) {
                            response.add(book);
                        }
                        break;
                    }
                    case 1: {
                        if (book.getNumberOfBoooks() > 0) {
                            response.add(book);
                        }
                        break;
                    }
                    case 2: {
                        if(book!=null && book.getLastPrice()!=null){
                        if ((book.getNumberOfBoooks() > 0) && (book.getLastPrice() > book.getBookPrice())) {
                            response.add(book);
                        }}
                        break;
                    }
                }
            }

        }

        try {
            if (minPrice.isPresent() && maxPrice.isPresent()) {
                    Integer minP = minPrice.get();
                    Integer maxP = maxPrice.get();
                response.removeIf(i->i.getBookPrice() < minP || i.getBookPrice() > maxP);
            }
        }catch(ConcurrentModificationException exx){
            throw new DBExceptions(exx.getMessage());
        }
        if (ratingMin.isPresent()) {
            Integer ratingMinim = ratingMin.get();
                    response.removeIf(i->i.getBookRating() < ratingMinim);

        }

        return new HashSet<Book>(response);
    }
}

