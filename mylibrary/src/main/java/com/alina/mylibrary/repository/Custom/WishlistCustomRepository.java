package com.alina.mylibrary.repository.Custom;

import com.alina.mylibrary.exception.QueryCustomException;
import com.alina.mylibrary.model.db.Wishlist;
import org.hibernate.QueryException;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.QueryTimeoutException;


@Repository
public class WishlistCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public Boolean DeleteWishlistByObj(Wishlist w) throws QueryCustomException{

    Object resp=null;
        try {
             resp=entityManager.createQuery("delete from Wishlist w where w.bookwishlist=:paramf and w.userwishlist=:params")
                    .setParameter("paramf", w.getBookwishlist().getBookId())
                    .setParameter("params", w.getUserwishlist().getUserId())
                    .getResultList();
        } catch (StackOverflowError e) {
            throw new QueryCustomException();
        } catch (
                QueryTimeoutException ex) {
            throw new QueryCustomException();
        } catch (
                QueryException ex) {
            throw new QueryCustomException();
        } catch (
                QueryCreationException ex) {
            throw new QueryCustomException();
        } catch (Exception ex) {
            throw new QueryCustomException();

        }

        if(resp==null){
            return false;
        }
        return true;

    }
}
