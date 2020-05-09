package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.dashboard.DashboardClass;
import com.alina.mylibrary.model.dashboard.DashboardThreeItemsClass;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardDao {
    public List<DashboardClass> getCategoriesWithNumberBooks() throws DaoException ;
    public List<DashboardClass> getAuthorsNumberBooks()  throws DaoException;
    public List<DashboardThreeItemsClass> getBooksWithAuthorsAndCat() throws DaoException;
}
