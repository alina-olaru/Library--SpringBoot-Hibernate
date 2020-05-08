package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.DashboardDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.dashboard.CategoryNumberBooks;
import com.alina.mylibrary.service.Interfaces.Admin.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DashboardServiceImp implements DashboardService {

    @Autowired
    DashboardDao dashboardDao;

    @Override
    public List<CategoryNumberBooks> getCategoriesWithNumberBooks() throws DBExceptions {
        try {
            return this.dashboardDao.getCategoriesWithNumberBooks();
        }catch (DaoException ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "CategoryNumberBooks obj", "Insert");

        }
        catch (Exception ex){
            throw new DBExceptions(ex.getMessage(), 404, this.getClass().getName(), "CategoryNumberBooks obj", "Insert");

        }
    }
}
