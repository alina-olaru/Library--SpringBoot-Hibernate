package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.model.dashboard.DashboardClass;
import com.alina.mylibrary.model.dashboard.DashboardThreeItemsClass;
import com.alina.mylibrary.model.db.Preferences;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Admin.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alina.mylibrary.controller.Interfaces.Admin.DashboardApi;

import java.util.List;


@Component
public class DashboardApiImp implements DashboardApi {


    @Autowired
    DashboardService dashboardService;


    @Override
    public ApiResponse<List<DashboardClass>> getCategoriesWithNumberBooks() {
       try{
           return new ApiResponse<List<DashboardClass>>(ApiResponseType.SUCCESS,this.dashboardService.getCategoriesWithNumberBooks(), "S-AU ADUS DATELE CU SUCCES");


       }catch (Exception e){
           return new ApiResponse<List<DashboardClass>>(ApiResponseType.ERROR,null,e.getMessage()+e.getLocalizedMessage()+ " a aparut o problema");
       }
    }

    @Override
    public  ApiResponse<List<DashboardClass>> getAuthorsNumberBooks() {

        try{
            return new ApiResponse<List<DashboardClass>>(ApiResponseType.SUCCESS,this.dashboardService.getAuthorsNumberBooks(), "S-AU ADUS DATELE CU SUCCES");


        }catch (Exception e){
            return new ApiResponse<List<DashboardClass>>(ApiResponseType.ERROR,null,e.getMessage()+e.getLocalizedMessage()+ " a aparut o problema");
        }

    }

    @Override
    public ApiResponse<List<DashboardThreeItemsClass>> getBooksWithAutCat() {
        try{
            return new ApiResponse<List<DashboardThreeItemsClass>>(ApiResponseType.SUCCESS,this.dashboardService.getBooksWithAuthorsAndCat(), "S-AU ADUS DATELE CU SUCCES");


        }catch (Exception e){
            return new ApiResponse<List<DashboardThreeItemsClass>>(ApiResponseType.ERROR,null,e.getMessage()+e.getLocalizedMessage()+ " a aparut o problema");
        }
    }

    @Override
    public ApiResponse<List<Preferences>> getPreferences(Integer userId) {

        try{
            return new ApiResponse<List<Preferences>>(ApiResponseType.SUCCESS,this.dashboardService.getPreferences(userId),"s-au adus cu succes");
        }catch (Exception ex){
            return new ApiResponse<List<Preferences>>(ApiResponseType.ERROR, null, ex.getClass() + " "+ ex.getMessage() + ex.getCause());

        }
    }
}
