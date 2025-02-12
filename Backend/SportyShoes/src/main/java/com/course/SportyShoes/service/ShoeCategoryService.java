package com.course.SportyShoes.service;

import com.course.SportyShoes.model.Shoe;
import com.course.SportyShoes.model.ShoeCategory;
import com.course.SportyShoes.repo.ShoeCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeCategoryService {

    @Autowired
    private ShoeCategoryRepo repo;

    public ShoeCategory createShoeCategory(ShoeCategory category) { return repo.save(category);}

    public void deleteCategory(Long id){
        repo.deleteById(id);
    }

    public Optional<ShoeCategory> getCategoryById(Long id){
        return repo.findById(id);
    }

    public List<ShoeCategory> getAllCategories() { return repo.findAll(); }

    public List<Shoe> getShoesInCategory(Long id) {
        return repo.findByCategoryId(id);
    }
}
