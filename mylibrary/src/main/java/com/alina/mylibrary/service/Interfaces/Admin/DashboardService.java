package com.alina.mylibrary.service.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.QueryCustomException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.dashboard.DashboardClass;
import com.alina.mylibrary.model.dashboard.DashboardThreeItemsClass;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DashboardService {

    public List<DashboardClass> getCategoriesWithNumberBooks() throws DBExceptions;
    public List<DashboardClass> getAuthorsNumberBooks()  throws DBExceptions;
    public List<DashboardThreeItemsClass> getBooksWithAuthorsAndCat() throws DBExceptions;

}
