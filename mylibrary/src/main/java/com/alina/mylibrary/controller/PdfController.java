package com.alina.mylibrary.controller;

import com.alina.mylibrary.model.db.BookOrder;
import com.alina.mylibrary.service.Interfaces.Guess.BookOrderService;
import com.alina.mylibrary.service.Services.Pdf.GeneratePdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class PdfController {








    @Autowired
    BookOrderService bookOrderService;

    @RequestMapping(value = "/public/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
//    @GetMapping("/public/pdfreport")
    public ResponseEntity<InputStreamResource> getInvoiceAll(@RequestBody BookOrder order) {

        //    var cities = (List<City>) cityService.findAll();
        List<BookOrder> orders=this.bookOrderService.getOrders();

        ByteArrayInputStream bis = GeneratePdfReport.generateInvoice(orders);

        var headers = new HttpHeaders();
        //By setting the Content-Disposition to inline, the PDF file is shown directly in browser.
        headers.add("Content-Disposition", "inline; filename=factura.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

        @RequestMapping(value = "/public/pdfreport/{id}", method = RequestMethod.GET,
          produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getInvoice(@RequestParam Integer orderId) {

    //    var cities = (List<City>) cityService.findAll();
       BookOrder order =this.bookOrderService.getOrderById(orderId);
        ByteArrayInputStream bis = GeneratePdfReport.generateInvoiceForUser(order);

        var headers = new HttpHeaders();
        //By setting the Content-Disposition to inline, the PDF file is shown directly in browser.
        headers.add("Content-Disposition", "inline; filename=factura.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
