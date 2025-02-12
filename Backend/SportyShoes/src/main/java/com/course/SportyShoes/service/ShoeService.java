package com.course.SportyShoes.service;

import com.course.SportyShoes.model.Shoe;
import com.course.SportyShoes.model.ShoeCategory;
import com.course.SportyShoes.repo.ShoeCategoryRepo;
import com.course.SportyShoes.repo.ShoeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {

    private static final String IMAGE_PATH="C:/images/";

    Logger logger = LoggerFactory.getLogger(ShoeService.class);

    @Autowired
    private ShoeRepo repo;

    public Shoe createShoe(Shoe shoe) {
        return repo.save(shoe);
    }

    public byte[] getShoeImage(String imagePath) throws IOException {
        File imageFile = new File(IMAGE_PATH+imagePath);

        if(!imageFile.exists()){
            throw new IOException("File does not exist");
        }

        return Files.readAllBytes(imageFile.toPath());
    }

    public Shoe createShoe(String name, String description, ShoeCategory category, Double price,MultipartFile file) throws IOException {
        File dir = new File(IMAGE_PATH);

        if(!dir.exists()){
            dir.mkdirs(); //if directory doesn't exist then make it
        }

        File destinationFile = new File(dir, file.getOriginalFilename());
        file.transferTo(destinationFile.toPath());

        logger.info("Saved to " + destinationFile.toPath());

        Shoe shoe = new Shoe();
        shoe.setName(name);
        shoe.setDescription(description);
        shoe.setCategory(category);
        shoe.setPrice(price);
        shoe.setImagePath(file.getOriginalFilename());

        return repo.save(shoe);
    }

    public void deleteShoe(Long id){
        repo.deleteById(id);
    }

    public Optional<Shoe> getShoeById(Long id){
        return repo.findById(id);
    }

    public List<Shoe> getShoesByName(String name){
        return repo.findByName(name);
    }

    public List<Shoe> getAllShoes() { return repo.findAll(); }

    public Shoe updateShoeCategory(Shoe shoe){
        Shoe oldShoe = repo.findById(shoe.getId()).orElse(null);
        oldShoe.setCategory(shoe.getCategory());
        return repo.save(oldShoe);
    }

    public Shoe updatePrice(Shoe shoe){
        Shoe oldShoe = repo.findById(shoe.getId()).orElse(null);
        oldShoe.setPrice(shoe.getPrice());
        return repo.save(oldShoe);
    }
}
