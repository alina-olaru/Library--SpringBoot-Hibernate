package com.alina.mylibrary.controller.impl.Admin;


import com.alina.mylibrary.controller.Interfaces.Guess.OrderApi;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookOrder;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.service.Interfaces.Guess.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class OrderApiImp implements OrderApi {


    @Autowired
    BookOrderService bookOrderService;

    @Override
    public ApiResponse<BookOrder> addOrder(@RequestBody  BookOrder order) {
        try {
            BookOrder response =  this.bookOrderService.addOrder(order);
            return new ApiResponse<BookOrder>(ApiResponseType.SUCCESS,response);

        }catch(Exception ex){
            return new ApiResponse<BookOrder>(ApiResponseType.ERROR,null);
        }
    }

    @Override
    public ApiResponse<List<BookOrder>> getOrdersByUser(Integer userId) {
        try {
            return new ApiResponse<List<BookOrder>>(ApiResponseType.SUCCESS, this.bookOrderService.getOrdersByUser(userId), "s-au adus datele cu succes");
        }catch (Exception ex){
            return new ApiResponse<List<BookOrder>>(ApiResponseType.ERROR,null, "   a aparut o eroare   " + ex.getCause() + "  "+ ex.getMessage() +"  "+ ex.getClass() + "  " + ex.getStackTrace());

        }


    }

    @Override
    public ApiResponse<List<BookOrder>> getOrders() {
        return new ApiResponse<List<BookOrder>>(ApiResponseType.SUCCESS,this.bookOrderService.getOrders());


    }


}
