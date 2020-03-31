package com.alina.mylibrary.service;


import com.alina.mylibrary.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public com.alina.mylibrary.model.Category addCategory( com.alina.mylibrary.model.Category category);
    List< Category> getCategories();
    Boolean deleteCategory(int categoryId);
    com.alina.mylibrary.model.Category updateCategory( com.alina.mylibrary.model.Category category);

}
