package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.DashboardApi;
import com.alina.mylibrary.model.dashboard.CategoryNumberBooks;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Admin.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DashboardApiImp implements DashboardApi {


    @Autowired
    DashboardService dashboardService;


    @Override
    public ApiResponse<List<CategoryNumberBooks>> getCategoriesWithNumberBooks() {
       try{
           return new ApiResponse<List<CategoryNumberBooks>>(ApiResponseType.SUCCESS,this.dashboardService.getCategoriesWithNumberBooks(), "S-AU ADUS DATELE CU SUCCES");


       }catch (Exception e){
           return new ApiResponse<List<CategoryNumberBooks>>(ApiResponseType.ERROR,null,e.getMessage()+e.getLocalizedMessage()+ " a aparut o problema");
       }
    }
}
