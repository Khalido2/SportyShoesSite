package com.course.SportyShoes.controller;

import com.course.SportyShoes.model.Shoe;
import com.course.SportyShoes.model.ShoeCategory;
import com.course.SportyShoes.service.ShoeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class ShoeCategoryController {

    @Autowired
    ShoeCategoryService service;

    @PostMapping("/add")
    public ResponseEntity<ShoeCategory> addCategory(@RequestParam("name") String name){
        ShoeCategory category = new ShoeCategory();
        category.setName(name);
        return ResponseEntity.status(HttpStatus.OK).body(service.createShoeCategory(category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> addCategory(@PathVariable Long id){
        service.deleteCategory(id);

        if(service.getCategoryById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Delete operation could not be completed");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShoeCategory>> getallCategories(){
        List<ShoeCategory> categories = service.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<ShoeCategory> getById(@PathVariable Long id){
        Optional<ShoeCategory> category = service.getCategoryById(id);

        return category.map(shoeCategory -> ResponseEntity.status(HttpStatus.OK).body(shoeCategory)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/shoes/{id}")
    public ResponseEntity<List<Shoe>> getShoesInCategory(@PathVariable Long id){
        List<Shoe> shoes = service.getShoesInCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(shoes);
    }
}
