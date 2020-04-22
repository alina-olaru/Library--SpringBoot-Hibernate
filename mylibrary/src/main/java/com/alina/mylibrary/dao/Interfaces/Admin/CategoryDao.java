package com.alina.mylibrary.dao.Interfaces.Admin;


import com.alina.mylibrary.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {

    public List<com.alina.mylibrary.model.Category> getCategories();
    public List<com.alina.mylibrary.model.Category> getCategoriesWithcategoryTitle(String categoryTitle);

    public com.alina.mylibrary.model.Category addCategory(com.alina.mylibrary.model.Category category);
    public com.alina.mylibrary.model.Category updateCategory(com.alina.mylibrary.model.Category category);
    public Boolean deleteCategory(int categoryId);
    public Boolean findById(Integer id);

}
