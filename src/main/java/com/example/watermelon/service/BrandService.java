package com.example.watermelon.service;

import com.example.watermelon.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    public List<Brand> findAll();
    public void save(Brand brand);
    public void deleteById(String id);
    public Optional<Brand> findById(String id);

}
