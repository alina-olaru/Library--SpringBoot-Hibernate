package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public Category addCategory(Category category)  throws DBExceptions;
    List< Category> getCategories();
    Boolean deleteCategory(int categoryId) throws DBExceptions;
    Category updateCategory(Category category) throws DBExceptions;

}
