package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.config.DBCheck;
import com.alina.mylibrary.dao.Interfaces.Admin.CategoryDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Category;
import com.alina.mylibrary.service.Interfaces.Admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {


    @Autowired
    CategoryDao categoryDao;


    @Override
    public Category addCategory(Category category) throws DBExceptions{
        if(category.equals(null)){
            throw new DBExceptions("Obiectul trimis este gol", 404, this.getClass().getName(), "Category obj", "Insert");

        }
        if (category != null) {

            List<Category> categoriesWithSameTitle=new ArrayList<>();
            try {
               categoriesWithSameTitle= this.categoryDao.getCategoriesWithcategoryTitle(category.getCategoryTitle());
            }catch (DaoException e){
                throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "category obj", "Insert");

            }
            if(categoriesWithSameTitle.size()>=1){
               throw new DBExceptions("Categoria este deja inregistrata",1,category.getClass().getName(),"categoryTitle","Insert");
                //the you already have that category
            }
            category.setCategoryTitle(DBCheck.Stringtify(category.getCategoryTitle()));
            Category response=null;
            try {
                response = categoryDao.addCategory(category);
                if(response!=null){
                    return response;
                }
            }catch(DaoException e){
                throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "category obj", "Insert");

            }

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
            throw new DBExceptions("id-ul nu poate fi gasit", 4, "categoryClass", "categoryId", "Delete");
        }
        Boolean rez=false;
        try{
            rez=this.categoryDao.findById(categoryId);
        }catch (DaoException e){
            throw new DBExceptions("id-ul nu poate fi gasit", 4, "categoryClass", "categoryId", "Delete");

        }
        if (rez == false) {
            throw new DBExceptions("id-ul nu poate fi gasit", 4, "categoryClass", "categoryId", "Delete");
        } else
            {
                try {
                    return this.categoryDao.deleteCategory(categoryId);
                }catch(DaoException e){
                    throw new DBExceptions(e.getMessage(),4,"categoryClass","category obj","delete");
                }
            }

        }


    @Override
    public Category updateCategory(Category category) throws DBExceptions {
    if(category.equals(null)){
        throw new DBExceptions("Obiect null",3,category.getClass().getName(),"category obj","Insert");

    }
        if (category != null) {

            List<Category> categoriesWithSameTitle=new ArrayList<>();
            try {
                categoriesWithSameTitle= this.categoryDao.getCategoriesWithcategoryTitle(category.getCategoryTitle());
            }catch (DaoException e){
                throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "category obj", "Insert");

            }
            if(categoriesWithSameTitle.size()>=1){
                throw new DBExceptions("Categoria este deja inregistrata",1,category.getClass().getName(),"categoryTitle","Insert");
                //the you already have that category
            }
            category.setCategoryTitle(DBCheck.Stringtify(category.getCategoryTitle()));
            Category response=null;
            try {
                response = categoryDao.updateCategory(category);
                if(response!=null){
                    return response;
                }
            }catch(DaoException e){
                throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "category obj", "Insert");

            }

        }
        return null;
    }
    }

