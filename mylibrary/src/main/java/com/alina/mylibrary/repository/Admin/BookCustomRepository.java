package com.alina.mylibrary.repository.Admin;


import com.alina.mylibrary.exception.QueryCustomException;
import com.alina.mylibrary.model.db.Author;
import com.alina.mylibrary.model.db.Book;
import org.hibernate.QueryException;
import org.hibernate.QueryParameterException;
import org.hibernate.hql.internal.QueryExecutionRequestException;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> deleteBookByAuthor(Author author) throws QueryCustomException{

        Author a=entityManager.find(Author.class,1);
        /**
         *
         * First we find all the books from dataBase that
         * this author wrote alone
         * (no other author exists in Book_Authors table)
         */
        List<Integer> bookId=new ArrayList<>();
        try {
            bookId=entityManager.createQuery("select ba.bookId.bookId FROM BookAuthors ba" +
                    "join Book b" +
                    "on ba.bookId.bookId=b.bookId" +
                    "join BookAuthors ba2" +
                    "on b.bookId=ba2.bookId.bookId" +
                    "where ba2.authorId=? " +
                    "group by ba.bookId.bookId" +
                    "having count(ba.authorId.authorId)=1")
                    .setParameter(1, a.getAuthorId())
                    .getResultList();
        }catch (StackOverflowError e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query","StackOverflowError","finding books writted only by this author",author.getClass().getName(),"get");
        }
        catch (QueryTimeoutException ex){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(),"QueryTimeoutException","finding books writted only by this author",author.getClass().getName(),"get");

        }
        catch (QueryException ex) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(), "QueryException", "finding books writted only by this author", author.getClass().getName(), "get");
        }

        catch(QueryCreationException ex){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(),"QueryCreationException","finding books writted only by this author",author.getClass().getName(),"get");

        }
        catch (Exception ex){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in primul query" + ex.getMessage(),"Exception","finding books writted only by this author",author.getClass().getName(),"get");

        }



        /**
         *
         * Then we delete all the associations from Book_Authors Table
         * that connects our author with a book
         */
        Query q=entityManager.createNativeQuery("Delete from BookAuthors ba" +
                "where ba.authorId=?");
                q.setParameter(1,a.getAuthorId());
                try
                {
                q.executeUpdate();
                }
                catch (StackOverflowError e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut problemein al doilea query","StackOverflowError","finding books writted only by this author",author.getClass().getName(),"get");
        }
        catch (QueryTimeoutException e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in al doilea query" + e.getMessage(),"QueryTimeoutException","finding books writted only by this author",author.getClass().getName(),"get");

        }
        catch (QueryException e) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in al doilea query" + e.getMessage(), "QueryException", "finding books writted only by this author", author.getClass().getName(), "get");
        }

        catch(QueryCreationException e){
                throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in al doilea query" + e.getMessage(),"QueryCreationException","finding books writted only by this author",author.getClass().getName(),"get");

            }
        catch (Exception e){
                throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in al doilea query" + e.getMessage(),"Exception","finding books writted only by this author",author.getClass().getName(),"get");

            }


        /**
         *
         *
         * Then we remove all books that this author wrote alone
         * (no other author exists in Book_Authors table)
         */

        Query q_final=entityManager.createNativeQuery("DELETE FROM Book b " +
                "where b.bookId in (:ids)");
        q_final.setParameter("ids",bookId);
        try {
            q_final.executeUpdate();
        }
        catch (StackOverflowError e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut problemein al treilea query","StackOverflowError","finding books writted only by this author",author.getClass().getName(),"get");
        }
        catch (QueryTimeoutException e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in al treilea query" + e.getMessage(),"QueryTimeoutException","finding books writted only by this author",author.getClass().getName(),"get");

        }
        catch (QueryException e) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in al treilea query" + e.getMessage(), "QueryException", "finding books writted only by this author", author.getClass().getName(), "get");
        }

        catch(QueryCreationException e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in al treilea query" + e.getMessage(),"QueryCreationException","finding books writted only by this author",author.getClass().getName(),"get");

        }
        catch (Exception e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme in al treilea query" + e.getMessage(),"Exception","finding books writted only by this author",author.getClass().getName(),"get");

        }
        entityManager.remove(a);

        Query response=entityManager.createQuery("select * from Book");
        try {
            response.executeUpdate();
            List<Book> bookResponse = response.getResultList();
            return bookResponse;
        }      catch (StackOverflowError e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme la adusul cartilor rezultat din baza de date","StackOverflowError","finding books writted only by this author",author.getClass().getName(),"get");
        }
        catch (QueryTimeoutException e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme la adusul cartilor rezultat din baza de date" + e.getMessage(),"QueryTimeoutException","finding books writted only by this author",author.getClass().getName(),"get");

        }
        catch (QueryException e) {
            throw new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme la adusul cartilor rezultat din baza de date" + e.getMessage(), "QueryException", "finding books writted only by this author", author.getClass().getName(), "get");
        }

        catch(QueryCreationException e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut problemela adusul cartilor rezultat din baza de date" + e.getMessage(),"QueryCreationException","finding books writted only by this author",author.getClass().getName(),"get");

        }
        catch (Exception e){
            throw  new QueryCustomException("Nu s-a putut sterge cartea,au aparut probleme la adusul cartilor rezultat din baza de date" + e.getMessage(),"Exception","finding books writted only by this author",author.getClass().getName(),"get");

        }

    }

    public List<Book> testQuery(String title){
        List<Book> books=entityManager.createQuery("select distinct b,p  from Book b " +
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
                "and lower(c.categoryTitle)=lower(:title)")
                .setParameter("title",title)
                .getResultList();


        return books;




    }
}
