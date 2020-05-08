package com.alina.mylibrary.service.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.dashboard.CategoryNumberBooks;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DashboardService {

    public List<CategoryNumberBooks> getCategoriesWithNumberBooks() throws DBExceptions;
}
