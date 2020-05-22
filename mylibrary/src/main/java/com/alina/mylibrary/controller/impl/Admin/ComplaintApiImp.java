package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Guess.ComplaintApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.Complaint;
import com.alina.mylibrary.service.Interfaces.Admin.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.List;


@Component
public class ComplaintApiImp implements ComplaintApi {

    /**
     *
     *<h2>complaints</h2>
     *<p>Rest api for user complaints(from user interface) </p>
     * <p>each user can send a complaint / a message to the
     * library via this future</p>
     *@see <a href=""></a>
     * since 3.0.0
     */

    //TODO ADD LINK

    @Autowired
    ComplaintService complaintService;


    @Override
    public ApiResponse<List<Complaint>> getComplaints() {
     List<Complaint> response=this.complaintService.getComplaints();
     return new ApiResponse<List<Complaint>>(ApiResponseType.SUCCESS,response);
    }

    @Override
    public ApiResponse<Complaint> insertComplaint(Complaint complaint) {


        Complaint response=null;
        try {
            response = this.complaintService.addComplaint(complaint);
            if(response!=null){
                return new ApiResponse<Complaint>(ApiResponseType.SUCCESS,response,"S-a adaugat plangerea cu succes");

            }
        }catch (DBExceptions e){
            return new ApiResponse<Complaint>(ApiResponseType.ERROR,null,e.message);

        }catch (SQLException e){
            return new ApiResponse<Complaint>(ApiResponseType.ERROR,null,e.getMessage());
        }

        catch (Exception e){
            return new ApiResponse<Complaint>(ApiResponseType.ERROR,null,"Cererea nu a putut fi inregistrata");

        }
        return new ApiResponse<Complaint>(ApiResponseType.ERROR,response,"Cererea nu a putut fi inregistrata");

    }

    @Override
    public ApiResponse<List<Complaint>> getComplaintById(@PathVariable Integer id) {
        try {
            return new  ApiResponse<List<Complaint>> (ApiResponseType.SUCCESS,this.complaintService.getComplaintsbyId(id),"s-au adus datele cu succces");
        }catch (Exception ex){
            return new ApiResponse<List<Complaint>>(ApiResponseType.ERROR,null,ex.getMessage() +"  " + ex.getLocalizedMessage() +"  " + ex.getCause() +"  " + ex.getCause() +"  " + ex.getClass() +"  " +  ex.getStackTrace() +"  " + ex.fillInStackTrace());
        }
    }
}

