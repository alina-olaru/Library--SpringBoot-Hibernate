package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.dashboard.CategoryNumberBooks;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardDao {
    public List<CategoryNumberBooks> getCategoriesWithNumberBooks() throws DaoException ;
}
