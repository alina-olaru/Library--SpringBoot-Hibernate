package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.DashboardDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.dashboard.CategoryNumberBooks;
import com.alina.mylibrary.repository.Custom.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DashboardDaoImp implements DashboardDao {


    @Autowired
    DashboardRepository dashboardRepository;


    @Override
    public List<CategoryNumberBooks> getCategoriesWithNumberBooks() throws DaoException {
        try {
            return this.dashboardRepository.getCategoriesWithNumberBooks();
        } catch (Exception ex) {
            throw new DaoException(3);
        }
    }
}
