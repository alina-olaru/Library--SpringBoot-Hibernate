package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public com.alina.mylibrary.model.Category addCategory( com.alina.mylibrary.model.Category category)  throws DBExceptions;
    List< Category> getCategories();
    Boolean deleteCategory(int categoryId) throws DBExceptions;
    com.alina.mylibrary.model.Category updateCategory( com.alina.mylibrary.model.Category category) throws DBExceptions;

}
