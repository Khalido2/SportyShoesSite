package com.course.SportyShoes.repo;

import com.course.SportyShoes.model.Shoe;
import com.course.SportyShoes.model.ShoeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoeCategoryRepo extends JpaRepository<ShoeCategory, Long> {

    String sql1 = "select s from Shoe s join s.category c where c.id=?1";

    @Query(sql1)
    List<Shoe> findByCategoryId(Long id);
}
