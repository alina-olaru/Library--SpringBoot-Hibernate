package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.CategoryDao;
import com.alina.mylibrary.model.Category;
import com.alina.mylibrary.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CategoryDaoImp implements CategoryDao {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
       return this.categoryRepository.findAll();

    }

    @Override
    public Category addCategory(Category category) {
        if(category!=null){
            this.categoryRepository.save(category);
            return category;
        }
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        if(category!=null){
            this.categoryRepository.save(category);
            return category;
        }
        return null;
    }

    @Override
    public Boolean deleteCategory(int categoryId) {
        if(categoryId>=1){
            this.categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    @Override
    public List<Category> getCategoriesWithcategoryTitle(String categoryTitle) {
     return this.categoryRepository.findBycategoryTitle(categoryTitle);
    }
}
