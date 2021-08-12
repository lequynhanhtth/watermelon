package com.example.watermelon.service;

import com.example.watermelon.model.Brand;
import com.example.watermelon.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void deleteById(String id) {
brandRepository.deleteById(id);
    }

    @Override
    public Optional<Brand> findById(String id) {
        return brandRepository.findById(id);
    }
}
