package com.course.SportyShoes.repo;

import com.course.SportyShoes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo  extends JpaRepository<User, Long> {

    String sql1 = "select u from User u where u.name=?1";
    @Query(sql1)
    Optional<User> findByUsername(String username);
}
