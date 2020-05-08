package com.alina.mylibrary.repository.Custom;


import com.alina.mylibrary.exception.QueryCustomException;
import com.alina.mylibrary.model.dashboard.CategoryNumberBooks;
import org.hibernate.QueryException;
import org.hibernate.transform.Transformers;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryTimeoutException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DashboardRepository {

    @PersistenceContext
    private EntityManager entityManager;



    public List<CategoryNumberBooks> getCategoriesWithNumberBooks() throws QueryCustomException{
        List<CategoryNumberBooks> response=null;
        try{
            response=entityManager
                    .createQuery("select c.categoryTitle as numberBooksforCategory ,count(c.categoryId) as titleOfCategory \n" +
                    "from Category  c \n" +
                    "join BooksCategories bc\n" +
                    "on c.categoryId=bc.categories.categoryId \n" +
                    "group by c.categoryTitle")
                    .getResultList();


        }catch (StackOverflowError e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query","StackOverflowError","finding books writted only by this author",response.getClass().getName(),"get");
        }
        catch (QueryTimeoutException ex){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(),"QueryTimeoutException","finding books writted only by this author",response.getClass().getName(),"get");

        }
        catch (QueryException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryException", "finding books writted only by this author", response.getClass().getName(), "get");
        }

        catch(QueryCreationException ex){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(),"QueryCreationException","finding books writted only by this author",response.getClass().getName(),"get");

        }
        catch (Exception ex){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(),"Exception","finding books writted only by this author",response.getClass().getName(),"get");

        }

        return response;
    }
}

//
//        select CATEGORY_TITLE,count(*)
//        from CATEGORY c
//        join BOOKS_CATEGORIES bc
//        on c.CATEGORY_ID=bc.CATEGORIES___CATEGORY_ID
//        group by CATEGORY_TITLE;