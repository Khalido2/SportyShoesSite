package com.course.SportyShoes.controller;

import com.course.SportyShoes.model.User;
import com.course.SportyShoes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody User user){
        Optional<User> u = service.getUserByName(user.getName());

        if(u.isPresent() && u.get().getPassword().equals(user.getPassword())){
            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }

    @PutMapping("/users/updateRole")
    public User updateUserRole(@RequestParam("id") Long userID, @RequestParam("role") String userRole){
        return service.updateUserRole(userID, userRole);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return service.createuser(user);
    }

    @GetMapping("/users")
    public List<User> getallUsers(){
        return service.getAllusers();
    }
}
