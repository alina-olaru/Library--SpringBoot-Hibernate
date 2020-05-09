package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.DashboardDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.QueryCustomException;
import com.alina.mylibrary.model.dashboard.DashboardClass;
import com.alina.mylibrary.model.dashboard.DashboardThreeItemsClass;
import com.alina.mylibrary.repository.Custom.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DashboardDaoImp implements DashboardDao {


    @Autowired
    DashboardRepository dashboardRepository;


    @Override
    public List<DashboardClass> getCategoriesWithNumberBooks() throws DaoException {
        try {
            return this.dashboardRepository.getCategoriesWithNumberBooks();
        } catch (Exception ex) {
            throw new DaoException(3);
        }
    }

    @Override
    public List<DashboardClass> getAuthorsNumberBooks() throws DaoException {
        List<DashboardClass> response=null;
        try{
            response = this.dashboardRepository.getAuthorsNumberBooks();
            return response;
        }catch(QueryCustomException ex){
            throw new DaoException(3);
        }catch (Exception ex){
            throw new DaoException(3);
        }
    }

    @Override
    public List<DashboardThreeItemsClass> getBooksWithAuthorsAndCat() throws DaoException {
        List<DashboardThreeItemsClass> response = null;

        try{
            response = this.dashboardRepository.getBooksWithAuthorsAndCat();
            return response;
        }catch(QueryCustomException ex){
            throw new DaoException(3);
        }catch (Exception ex){
            throw new DaoException(3);
        }
    }
}
