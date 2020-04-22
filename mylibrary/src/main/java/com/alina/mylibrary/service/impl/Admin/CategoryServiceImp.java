package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.config.DBCheck;
import com.alina.mylibrary.dao.Interfaces.Admin.CategoryDao;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
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
    public Category addCategory(Category category) throws DBExceptions{
        if (category != null) {

            List< com.alina.mylibrary.model.Category> categoriesWithSameTitle=this.categoryDao.getCategoriesWithcategoryTitle(category.getCategoryTitle());
            if(categoriesWithSameTitle.size()>=1){
               throw new DBExceptions("Categoria este deja inregistrata",1,category.getClass().getName(),"categoryTitle","Insert");
                //the you already have that category
            }
            category.setCategoryTitle(DBCheck.Stringtify(category.getCategoryTitle()));
            return categoryDao.addCategory(category);
        }
        return null;
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryDao.getCategories();
    }

    @Override
    public Boolean deleteCategory(int categoryId) throws DBExceptions {
        if (categoryId < 1) {
            throw new DBExceptions("id-ul nu poate fi gasit", 2, "categoryClass", "categoryId", "Delete");
        }
        if (this.categoryDao.findById(categoryId) == false) {
            throw new DBExceptions("id-ul nu poate fi gasit", 2, "categoryClass", "categoryId", "Delete");
        } else if (this.categoryDao.findById(categoryId) == true) {
            {
                return this.categoryDao.deleteCategory(categoryId);
            }
        }
        return false;
    }

    @Override
    public Category updateCategory(Category category) throws DBExceptions {
        if (category != null) {

            List< com.alina.mylibrary.model.Category> categoriesWithSameTitle=this.categoryDao.getCategoriesWithcategoryTitle(category.getCategoryTitle());
            if(categoriesWithSameTitle.size()>=1){
                throw new DBExceptions("Categoria este deja inregistrata",1,category.getClass().getName(),"categoryTitle","Insert");
                //the you already have that category
            }
            category.setCategoryTitle(DBCheck.Stringtify(category.getCategoryTitle()));
            return categoryDao.updateCategory(category);
        }
        return null;
    }
    }

