package com.example.watermelon.service;

import com.example.watermelon.model.Product;
import com.example.watermelon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public Page<Product> findAllProductOrderByCreatedate(Pageable pageable) {
        return productRepository.findAllProductOrderByCreatedate(pageable);
    }

    @Override
    public Page<Product> findByPriceBetweenOrderByCreatedate(double min, double max, Pageable page) {
        return productRepository.findByPriceBetweenOrderByCreatedate(min, max, page);
    }

    @Override
    public List<Product> findByPriceBetweenOrderByCreatedate(double min, double max) {
        return productRepository.findByPriceBetweenOrderByCreatedate(min, max);
    }

    @Override
    public Page<Product> findByBrandOrderByCreatedate(String brand, Pageable pageable) {
        return productRepository.findByBrandOrderByCreatedateDesc(brand, pageable);
    }

    @Override
    public List<Product> findByBrandOrderByCreatedate(String brand) {
        return productRepository.findByBrandOrderByCreatedateDesc(brand);
    }

    @Override
    public Page<Product> findByNameOrderbyCreatedate(String name, Pageable page) {
        return productRepository.findByNameOrderByCreatedateDesc(name, page);
    }

    @Override
    public List<Product> findByNameOrderbyCreatedate(String name) {
        return productRepository.findByNameOrderByCreatedateDesc(name);
    }

    @Override
    public Page<Product> findByCategoryOrderByRate(String keyword, Pageable pageable) {
        return productRepository.findByCategoryOrderByRate(keyword, pageable);
    }


    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllSort(Sort sort) {
        return productRepository.findAllSort(sort);
    }
}
