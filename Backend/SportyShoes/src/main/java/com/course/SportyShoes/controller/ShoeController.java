package com.course.SportyShoes.controller;

import com.course.SportyShoes.model.Shoe;
import com.course.SportyShoes.model.ShoeCategory;
import com.course.SportyShoes.service.ShoeCategoryService;
import com.course.SportyShoes.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shoes")
@CrossOrigin(origins = "*")
public class ShoeController {

    @Autowired
    private ShoeService service;

    @Autowired
    private ShoeCategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Shoe> addShoe(@RequestParam("name") String name, @RequestParam("description") String description,  @RequestParam("categoryId") Long categoryId, @RequestParam("price") Double price, @RequestParam("image")MultipartFile file) throws IOException {
        Optional<ShoeCategory> category = categoryService.getCategoryById(categoryId);

        if(category.isPresent()){
            Shoe response = service.createShoe(name, description, category.get(), price,file);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/updatePrice")
    public ResponseEntity<Shoe> updateJobTitle(@RequestParam("id") Long id, @RequestParam("price") Double price){
        Shoe shoe = new Shoe();
        shoe.setPrice(price);
        shoe.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(service.updatePrice(shoe));
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<Shoe> updateJobTitle(@RequestParam("id") Long id, @RequestParam("categoryId") Long categoryId){
        Shoe shoe = new Shoe();
        Optional<ShoeCategory> category = categoryService.getCategoryById(categoryId);
        shoe.setId(id);

        if(category.isPresent()){
            shoe.setCategory(category.get());
            return ResponseEntity.status(HttpStatus.OK).body(service.updateShoeCategory(shoe));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/getImage/{imagePath}")
    public ResponseEntity<byte[]> getShoeImage(@PathVariable String imagePath) throws IOException{
        byte[] response = service.getShoeImage(imagePath);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Shoe>> getAllShoes() throws IOException{
        List<Shoe> response = service.getAllShoes();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> addShoe(@PathVariable Long id){
        service.deleteShoe(id);

        if(service.getShoeById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Delete operation could not be completed");
    }
}
