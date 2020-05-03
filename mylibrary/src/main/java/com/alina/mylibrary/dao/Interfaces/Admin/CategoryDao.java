package com.alina.mylibrary.dao.Interfaces.Admin;


import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {

    public List<Category> getCategories();
    public List<Category> getCategoriesWithcategoryTitle(String categoryTitle) throws DaoException;

    public Category addCategory(Category category) throws DaoException;
    public Category updateCategory(Category category) throws DaoException;
    public Boolean deleteCategory(int categoryId) throws DaoException;
    public Boolean findById(Integer id) throws DaoException;

}
