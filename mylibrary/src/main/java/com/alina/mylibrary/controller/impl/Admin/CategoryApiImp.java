package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.CategoryApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.Category;
import com.alina.mylibrary.service.Interfaces.Admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryApiImp implements CategoryApi {


    /**
     *
     *
     *<p>Rest api for categories(only CRUD operations ) from admin interfaces</p>
     * @see <a href="http://localhost:4200/admin/categorii"></a>
     * since 1.0.0
     */

    @Autowired
    CategoryService categoryService;

    //  com.alina.mylibrary.model.db.Category
    @Override
    public ApiResponse<List<Category>> getCategories() {
        List<Category> response = this.categoryService.getCategories();
        return new ApiResponse<List<Category>>(ApiResponseType.SUCCESS, response);
    }

    @Override
    public ApiResponse<Category> insertCategory(Category category) {
        if (category == null) {
            return new ApiResponse<Category>(ApiResponseType.ERROR, null, "Nu s-a putut adauga");
        }

        Category response = null;
        try {
            response = this.categoryService.addCategory(category);
            if (response != null) {
                return new ApiResponse<Category>(ApiResponseType.SUCCESS, response);
            }
        } catch (DBExceptions e) {
            return new ApiResponse<Category>(ApiResponseType.ERROR, response, e.message);
        }
        return new ApiResponse<Category>(ApiResponseType.ERROR, response, "Nu s-a putut insera");

    }

    @Override
    public ApiResponse<Category> updateCategory(Category category) {
        if (category == null) {
            return new ApiResponse<Category>(ApiResponseType.ERROR, null, "Nu s-a putut adauga");
        }

        Category response = null;
        try {
            response = this.categoryService.updateCategory(category);
            if (response != null) {
                return new ApiResponse<Category>(ApiResponseType.SUCCESS, response);
            }
        } catch (DBExceptions e) {
            return new ApiResponse<Category>(ApiResponseType.ERROR, response, e.message);
        }
        return new ApiResponse<Category>(ApiResponseType.ERROR, null, "Nu s-a putut adauga");

    }



    @Override
    public ApiResponse<Boolean> deleteCategory(int id) {


          Boolean response =null;
          try{
             response= this.categoryService.deleteCategory(id);
             if(response==true){
                 return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
             }

          }
          catch (DBExceptions e){
              return new ApiResponse<Boolean>(ApiResponseType.ERROR,response,e.message);
          }
          return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);


//      return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,"Nu s-a putut inregistra");
    }
}
