package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.CategoryApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Category;
import com.alina.mylibrary.service.Interfaces.Admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryApiImp implements CategoryApi {


    @Autowired
    CategoryService categoryService;

    //  com.alina.mylibrary.model.Category
    @Override
    public ApiResponse<List<Category>> getCategories() {
        List<com.alina.mylibrary.model.Category> response = this.categoryService.getCategories();
        return new ApiResponse<List<com.alina.mylibrary.model.Category>>(ApiResponseType.SUCCESS, response);
    }

    @Override
    public ApiResponse<Category> insertCategory(Category category) {
        if (category == null) {
            return new ApiResponse<Category>(ApiResponseType.ERROR, null, "Nu s-a putut adauga");
        }

        com.alina.mylibrary.model.Category response = null;
        try {
            response = this.categoryService.addCategory(category);
            if (response != null) {
                return new ApiResponse<com.alina.mylibrary.model.Category>(ApiResponseType.SUCCESS, response);
            }
        } catch (DBExceptions e) {
            System.out.println(e);
            return new ApiResponse<com.alina.mylibrary.model.Category>(ApiResponseType.ERROR, response, e.message);
        }
        return new ApiResponse<com.alina.mylibrary.model.Category>(ApiResponseType.ERROR, response, "Nu s-a putut insera");

    }

    @Override
    public ApiResponse<Category> updateCategory(Category category) {
        if (category == null) {
            return new ApiResponse<Category>(ApiResponseType.ERROR, null, "Nu s-a putut adauga");
        }

        com.alina.mylibrary.model.Category response = null;
        try {
            response = this.categoryService.updateCategory(category);
            if (response != null) {
                return new ApiResponse<com.alina.mylibrary.model.Category>(ApiResponseType.SUCCESS, response);
            }
        } catch (DBExceptions e) {
            return new ApiResponse<com.alina.mylibrary.model.Category>(ApiResponseType.ERROR, response, e.message);
        }
        return new ApiResponse<Category>(ApiResponseType.ERROR, null, "Nu s-a putut adauga");

    }



    @Override
    public ApiResponse<Boolean> deleteCategory(int id) {


          Boolean response =null;
          try{
              this.categoryService.deleteCategory(id);
          }
          catch (DBExceptions e){
              return new ApiResponse<Boolean>(ApiResponseType.ERROR,response,e.message);
          }
          return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);


//      return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,"Nu s-a putut inregistra");
    }
}
