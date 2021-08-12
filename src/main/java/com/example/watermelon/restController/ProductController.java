package com.example.watermelon.restController;

import com.example.watermelon.model.Category;
import com.example.watermelon.model.Product;
import com.example.watermelon.service.CategoryService;
import com.example.watermelon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService dao;
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(dao.findAll());
    }
}
