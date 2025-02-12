package com.course.SportyShoes.repo;

import com.course.SportyShoes.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoeRepo  extends JpaRepository<Shoe, Long> {

    String sql1 = "select s from Shoe s where s.name=?1";

    @Query(sql1)
    List<Shoe> findByName(String name);
}
