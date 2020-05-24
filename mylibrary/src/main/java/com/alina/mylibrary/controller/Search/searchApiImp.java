package com.alina.mylibrary.controller.Search;

import com.alina.mylibrary.exception.ServiceException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Search.searchService;
import org.hibernate.engine.spi.ExceptionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.io.OptionalDataException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class searchApiImp implements searchApi {

    @Autowired
    public searchService searchService;

    @Override
    public ApiResponse<HashSet<Book>> search(String query) {
       try{
           return new ApiResponse<HashSet<Book>>(ApiResponseType.SUCCESS,this.searchService.search(query),"s-au adus rezultatele cu succes");
       }catch (Exception ex){
           return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR,null,ex.getMessage() + ex.getStackTrace() + ex.getCause() + ex.getSuppressed());

       }
    }

    @Override
    public ApiResponse<HashSet<Book>> filter(Optional<Integer> disponibility, Optional<Integer> minPrice, Optional<Integer> maxPrice, Optional<Integer> ratingMin, Optional<List<Integer>> authors, Optional<List<Integer>> categories, Optional<List<Integer>> publishers) {

        try{
            return new ApiResponse<HashSet<Book>>(ApiResponseType.SUCCESS,this.searchService.filter(disponibility , minPrice , maxPrice,ratingMin,authors,categories,publishers),"s-au adus cartile filtrate dupa cerinte");
        }catch (ServiceException ex){
            return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR,null,"A aparut o problema" + ex.getLocalizedMessage() +ex.getCause() + ex.getMessage() + ex.getStackTrace());
        }catch (OutOfMemoryError ex){
            return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR,null,"A aparut o problema" + ex.getLocalizedMessage() +ex.getCause() + ex.getMessage() + ex.getStackTrace() + ex.getSuppressed());

        }catch (Exception ex){
            return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR,null,"A aparut o problema" + ex.getLocalizedMessage() +ex.getCause() + ex.getMessage() + ex.getStackTrace());
        }

    }

    @Override
    public ApiResponse<HashSet<Book>> filterwithWord(Optional<Integer> disponibility, Optional<Integer> minPrice, Optional<Integer> maxPrice, Optional<Integer> ratingMin, Optional<List<Integer>> authors, Optional<List<Integer>> categories, Optional<List<Integer>> publishers, String query) {
        try{
            return new ApiResponse<HashSet<Book>>(ApiResponseType.SUCCESS,this.searchService.filterWithTitle(disponibility , minPrice , maxPrice,ratingMin,authors,categories,publishers,query),"s-au adus cartile filtrate dupa cerinte");
        }catch (ServiceException ex){
            return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR,null,"A aparut o problema" + ex.getLocalizedMessage() +ex.getCause() + ex.getMessage() + ex.getStackTrace());
        }catch (OutOfMemoryError ex){
            return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR,null,"A aparut o problema" + ex.getLocalizedMessage() +ex.getCause() + ex.getMessage() + ex.getStackTrace() + ex.getSuppressed());

        }catch (Exception ex){
            return new ApiResponse<HashSet<Book>>(ApiResponseType.ERROR,null,"A aparut o problema" + ex.getLocalizedMessage() +ex.getCause() + ex.getMessage() + ex.getStackTrace());
        }

    }
    }

