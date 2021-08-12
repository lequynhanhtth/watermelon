package com.example.watermelon.service;

import com.example.watermelon.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> findAll();

    public void save(Category category);

    public void delete(String id);

    public Optional<Category> findById(String id);
}
