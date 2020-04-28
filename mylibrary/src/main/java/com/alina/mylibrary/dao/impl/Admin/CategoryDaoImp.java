package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.CategoryDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.Category;
import com.alina.mylibrary.repository.Admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CategoryDaoImp implements CategoryDao {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
       return this.categoryRepository.findAll();

    }

    @Override
    public Category addCategory(Category category) throws DaoException {
       try {
           this.categoryRepository.save(category);
           return category;
       }catch(Exception e){
           throw new DaoException(1);
       }


    }

    @Override
    public Category updateCategory(Category category) throws DaoException {
        try {
            this.categoryRepository.save(category);
            return category;
        }catch(Exception e){
            throw new DaoException(2);
        }


    }

    @Override
    public Boolean deleteCategory(int categoryId) throws DaoException{
    try{
            this.categoryRepository.deleteById(categoryId);
            return true;
        }catch (Exception e){
        throw new DaoException(4);
    }

    }

    @Override
    public List<Category> getCategoriesWithcategoryTitle(String categoryTitle) {
     return this.categoryRepository.findBycategoryTitle(categoryTitle);
    }

    public Boolean findById(Integer id)throws DaoException{
        try {
            this.categoryRepository.findById(id);
            return true;
        }catch (Exception e){
            throw new DaoException(3);
        }

       }

    }

