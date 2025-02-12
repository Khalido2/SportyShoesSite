package com.course.SportyShoes.repo;

import com.course.SportyShoes.model.PaymentReceipt;
import com.course.SportyShoes.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentReceiptRepo  extends JpaRepository<PaymentReceipt, Long> {
    String sql1 = "select p from PaymentReceipt p join p.itemPurchased s join s.category c where c.id=?1";

    @Query(sql1)
    List<PaymentReceipt> findByCategoryId(Long id);
}
