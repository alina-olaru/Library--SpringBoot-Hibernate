package com.alina.mylibrary.dao.Interfaces.Admin;


import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {

    public List<com.alina.mylibrary.model.Category> getCategories();
    public List<com.alina.mylibrary.model.Category> getCategoriesWithcategoryTitle(String categoryTitle) throws DaoException;

    public com.alina.mylibrary.model.Category addCategory(com.alina.mylibrary.model.Category category) throws DaoException;
    public com.alina.mylibrary.model.Category updateCategory(com.alina.mylibrary.model.Category category) throws DaoException;
    public Boolean deleteCategory(int categoryId) throws DaoException;
    public Boolean findById(Integer id) throws DaoException;

}
