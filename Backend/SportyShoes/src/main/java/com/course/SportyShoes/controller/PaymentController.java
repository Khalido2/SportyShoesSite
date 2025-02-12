package com.course.SportyShoes.controller;

import com.course.SportyShoes.model.PaymentReceipt;
import com.course.SportyShoes.model.User;
import com.course.SportyShoes.service.PaymentReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentReceiptService service;

    @PostMapping("/add")
    public PaymentReceipt addPayment(@RequestBody PaymentReceipt paymentReceipt){
        return service.createPaymentReceipt(paymentReceipt);
    }

    @GetMapping("/byCategoryId/{id}")
    public List<PaymentReceipt> getByCategoryId(@PathVariable Long id){
        return service.findByCategoryId(id);
    }

    @GetMapping("/all")
    public List<PaymentReceipt> getAllReceipts(){
        return service.getAllReceipts();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> addShoe(@PathVariable Long id){
        service.deletePaymentReceipt(id);

        if(service.getById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Delete operation could not be completed");
    }
}
