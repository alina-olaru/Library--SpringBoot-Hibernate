package com.alina.mylibrary.repository.Custom;

import com.alina.mylibrary.exception.QueryCustomException;
import com.alina.mylibrary.model.dashboard.DashboardClass;
import com.alina.mylibrary.model.dashboard.DashboardThreeItemsClass;
import com.alina.mylibrary.model.db.Preferences;
import org.hibernate.QueryException;
import org.hibernate.transform.Transformers;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DashboardRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<DashboardClass> getCategoriesWithNumberBooks() throws QueryCustomException {
        List<DashboardClass> response = null;
        try {
            TypedQuery<DashboardClass> query = entityManager.createQuery("select NEW  com.alina.mylibrary.model.dashboard.DashboardClass(c.categoryTitle as titleOfCategory  ,count(c.categoryId) as numberBooksforCategory) \n" +
                    "from Category  c \n" +
                    "join BooksCategories bc\n" +
                    "on c.categoryId=bc.categories.categoryId \n" +
                    "group by c.categoryTitle", DashboardClass.class);

            response = query.getResultList(); //no type warning
            return response;

        } catch (StackOverflowError e) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query", "StackOverflowError", "finding books writted only by this author", response.getClass().getName(), "get");
        } catch (QueryTimeoutException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryTimeoutException", "finding books writted only by this author", response.getClass().getName(), "get");

        } catch (QueryException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryException", "finding books writted only by this author", response.getClass().getName(), "get");
        } catch (QueryCreationException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryCreationException", "finding books writted only by this author", response.getClass().getName(), "get");

        } catch (Exception ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "Exception", "finding books writted only by this author", response.getClass().getName(), "get");

        }


    }
    public List<Preferences> getPreferences(Integer userId) throws QueryCustomException{
        List<Preferences> preferences=null;
        try{
            TypedQuery<Preferences> query=entityManager.createQuery("SELECT NEW Preferences(a.firstName as first_name , a.lastName as last_name  ) from " +
                    "Book b " +
                    "join Whishlist w \n" +
                    "on b.bookId=w.bookwishlist.bookId \n" +
                    "join BooksAuthors ba \n" +
                    "on ba.bookId.bookId=b.bookId \n" +
                    "join Author a \n" +
                    "on a.authorId=ba.authorId.authorId \n" +
                    "where w.userwishlist.userId=? ",
                    Preferences.class)
                    .setParameter(1,userId);
            preferences = query.getResultList();
            return preferences;
        } catch (StackOverflowError e) {
            throw new QueryCustomException("Nu s-au putut aduce datele,probleme la query", "StackOverflowError", "preferences", preferences.getClass().getName(), "get");
        } catch (QueryTimeoutException ex) {
            throw new QueryCustomException("Nu s-au putut aduce datele,probleme la query" + ex.getMessage(), "QueryTimeoutException","preferences", preferences.getClass().getName(), "get");

        } catch (QueryException ex) {
            throw new QueryCustomException("Nu s-au putut aduce datele,probleme la query"+ ex.getMessage(), "QueryException", "preferences", preferences.getClass().getName(), "get");
        } catch (QueryCreationException ex) {
            throw new QueryCustomException("Nu s-au putut aduce datele,probleme la query" + ex.getMessage(), "QueryCreationException", "preferences", preferences.getClass().getName(), "get");

        } catch (Exception ex) {
            throw new QueryCustomException("Nu s-au putut aduce datele,probleme la query" + ex.getMessage(), "Exception","preferences", preferences.getClass().getName(), "get");

        }


    }
    public List<DashboardClass> getAuthorsNumberBooks() throws QueryCustomException{


        List<DashboardClass> response = null;
        try {

            TypedQuery<DashboardClass> query = entityManager.createQuery("select NEW  com.alina.mylibrary.model.dashboard.DashboardClass(concat(concat(a.firstName, ' '),a.lastName) as titleOfCategory  ,count(a.authorId) as numberBooksforCategory) \n" +
                    "from Author a\n" +
                    "join BooksAuthors ba \n" +
                    "on a.authorId=ba.authorId \n" +
                    "group by concat(concat(a.firstName, ' '),a.lastName)", DashboardClass.class);

            response = query.getResultList(); //no type warning
            return response;

        } catch (StackOverflowError e) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query", "StackOverflowError", "finding books writted only by this author", response.getClass().getName(), "get");
        } catch (QueryTimeoutException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryTimeoutException", "finding books writted only by this author", response.getClass().getName(), "get");

        } catch (QueryException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryException", "finding books writted only by this author", response.getClass().getName(), "get");
        } catch (QueryCreationException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryCreationException", "finding books writted only by this author", response.getClass().getName(), "get");

        } catch (Exception ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "Exception", "finding books writted only by this author", response.getClass().getName(), "get");

        }


    }
    public List<DashboardThreeItemsClass> getBooksWithAuthorsAndCat() throws QueryCustomException{

        ///TODO DE CE NU MERGE ASTA

        List<DashboardThreeItemsClass> response = null;
        try {
           response = entityManager.createNativeQuery("select nrcat,narath,t1.title\n" +
                            "from (\n" +
                            "select b.BOOK_TITLE title ,count(BC.CATEGORIES___CATEGORY_ID) nrcat\n" +
                            "FROM [MyLibrary].[dbo].[BOOK]  as b\n" +
                            "join [MyLibrary].[dbo].[BOOKS_CATEGORIES] as bc\n" +
                            "on  bc.BOOKS_C___BOOK_ID=b.BOOK_ID\n" +
                            "group by b.BOOK_TITLE\n" +
                            ") t1 \n" +
                            "join (\n" +
                            "select b.BOOK_TITLE as \"title\" , COUNT(ba.AUTHOR_ID___AUTHOR_ID) as narath\n" +
                            "FROM [MyLibrary].[dbo].[BOOK]  as b\n" +
                            "join [MyLibrary].[dbo].[BOOKS_AUTHORS] as ba\n" +
                            "on b.BOOK_ID=ba.BOOK_ID___BOOK_ID\n" +
                            "group by b.BOOK_TITLE\n" +
                            "\n" +
                            ") t2\n" +
                            "on t1.title=t2.title",DashboardThreeItemsClass.class).getResultList();


            return response;

        } catch (StackOverflowError e) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query", "StackOverflowError", "finding books writted only by this author", response.getClass().getName(), "get");
        } catch (QueryTimeoutException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryTimeoutException", "finding books writted only by this author", response.getClass().getName(), "get");

        } catch (QueryException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryException", "finding books writted only by this author", response.getClass().getName(), "get");
        } catch (QueryCreationException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryCreationException", "finding books writted only by this author", response.getClass().getName(), "get");

        } catch (Exception ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "Exception", "finding books writted only by this author", response.getClass().getName(), "get");

        }



    }
}
