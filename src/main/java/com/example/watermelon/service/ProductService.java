package com.example.watermelon.service;

import com.example.watermelon.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public List<Product> findAll();

    public Page<Product> findAll(Pageable pageable);

    public List<Product> findAll(Sort sort);

    public Page<Product> findAllProductOrderByCreatedate(Pageable pageable);

    public Page<Product> findByPriceBetweenOrderByCreatedate(double min, double max, Pageable page);

    public List<Product> findByPriceBetweenOrderByCreatedate(double min, double max);

    public Page<Product> findByBrandOrderByCreatedate(String brand, Pageable pageable);

    public List<Product> findByBrandOrderByCreatedate(String brand);

    public Page<Product> findByNameOrderbyCreatedate(String name, Pageable page);

    public List<Product> findByNameOrderbyCreatedate(String name);

    public Page<Product> findByCategoryOrderByRate(String keyword, Pageable pageable);

    public List<Product> findAllSort(Sort sort);

    public void save(Product product);

    public void delete(long id);

    public Optional<Product> findById(long id);

}
