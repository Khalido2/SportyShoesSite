package com.course.SportyShoes.service;

import com.course.SportyShoes.model.User;
import com.course.SportyShoes.model.UserRole;
import com.course.SportyShoes.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepo repo;

    public User createuser(User user) { return repo.save(user);}

    public void deleteUser(Long id){
        repo.deleteById(id);
    }

    public Optional<User> getUserById(Long id){
        return repo.findById(id);
    }

    public List<User> getAllusers() { return repo.findAll(); }

    public User updatePassword(User user){
        User oldUser = repo.findById(user.getId()).orElse(null);
        oldUser.setPassword(user.getPassword());
        return repo.save(oldUser);
    }

    public Optional<User> getUserByName(String username){
        return repo.findByUsername(username);
    }

    public User updateUserRole(Long id, String role){
        User oldUser = repo.findById(id).orElse(null);
        oldUser.setUserRole(UserRole.valueOf(role));
        return repo.save(oldUser);
    }
}
