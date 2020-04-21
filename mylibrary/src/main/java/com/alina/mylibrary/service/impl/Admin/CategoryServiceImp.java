package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.CategoryDao;
import com.alina.mylibrary.model.Category;
import com.alina.mylibrary.service.Interfaces.Admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {


    @Autowired
    CategoryDao categoryDao;


    @Override
    public Category addCategory(Category category) {
        if (category != null) {

            List< com.alina.mylibrary.model.Category> categoriesWithSameTitle=this.categoryDao.getCategoriesWithcategoryTitle(category.getCategoryTitle());
            if(categoriesWithSameTitle.size()>=1){
                return null;
                //the you already have that category
            }
            return categoryDao.addCategory(category);
        }
        return null;
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryDao.getCategories();
    }

    @Override
    public Boolean deleteCategory(int categoryId) {
       return this.categoryDao.deleteCategory(categoryId);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryDao.updateCategory(category);
    }
}
