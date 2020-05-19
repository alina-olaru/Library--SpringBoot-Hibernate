package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.DashboardDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.dashboard.DashboardClass;
import com.alina.mylibrary.model.dashboard.DashboardThreeItemsClass;
import com.alina.mylibrary.model.db.Preferences;
import com.alina.mylibrary.service.Interfaces.Admin.DashboardService;
import org.hibernate.type.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DashboardServiceImp implements DashboardService {

    @Autowired
    DashboardDao dashboardDao;

    @Override
    public List<DashboardClass> getCategoriesWithNumberBooks() throws DBExceptions {
        try {
            return this.dashboardDao.getCategoriesWithNumberBooks();
        }catch (DaoException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj", "Insert");

        }
        catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj", "Insert");

        }
    }

    @Override
    public List<DashboardClass> getAuthorsNumberBooks() throws DBExceptions {
        try{
            return this.dashboardDao.getAuthorsNumberBooks();
        }catch (DaoException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj", "Insert");

        }catch (SerializationException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj,probleme la serializare!!", "Insert");

        }catch(SerializationFailedException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj , probleme la serializare!", "Insert");
        }
        catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj", "Insert");

        }
    }

    @Override
    public List<DashboardThreeItemsClass> getBooksWithAuthorsAndCat() throws DBExceptions {
        try{
            return this.dashboardDao.getBooksWithAuthorsAndCat();
        }catch (DaoException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj", "Insert");

        }catch (SerializationException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj,probleme la serializare!!", "Insert");

        }catch(SerializationFailedException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj , probleme la serializare!", "Insert");
        }
        catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "DashboardClass obj", "Insert");

        }
    }

    @Override
    public List<Preferences> getPreferences(Integer userId) throws DBExceptions {
        try{
            return this.dashboardDao.getPreferences(userId);
        }catch (DaoException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "Preferences obj", "get");

        }catch (SerializationException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "Preferences obj,probleme la serializare!!", "get");

        }catch(SerializationFailedException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "Preferences obj , probleme la serializare!", "get");
        }
        catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "Preferences obj", "get");

        }
    }
}
