package com.example.watermelon.repository;

import com.example.watermelon.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,String> {
}
