package com.course.SportyShoes.service;

import com.course.SportyShoes.model.PaymentReceipt;
import com.course.SportyShoes.model.Shoe;
import com.course.SportyShoes.repo.PaymentReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentReceiptService {

    @Autowired
    private PaymentReceiptRepo repo;

    public PaymentReceipt createPaymentReceipt(PaymentReceipt paymentReceipt) { return repo.save(paymentReceipt);}

    public void deletePaymentReceipt(Long id){
        repo.deleteById(id);
    }

    public Optional<PaymentReceipt> getById(Long id){
        return repo.findById(id);
    }

    public List<PaymentReceipt> getAllReceipts() { return repo.findAll(); }

    public List<PaymentReceipt> findByCategoryId(Long id){
        return repo.findByCategoryId(id);
    }
}
