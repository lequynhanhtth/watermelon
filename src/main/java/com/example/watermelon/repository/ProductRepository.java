package com.example.watermelon.repository;

import com.example.watermelon.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select  o from Product  o where o.status=true order by o.createdate DESC")
    Page<Product> findAllProductOrderByCreatedate(Pageable pageable);

    @Query("select o from Product o where o.status=true and o.price between ?1 and ?2  order by o.createdate DESC")
    Page<Product> findByPriceBetweenOrderByCreatedate(double min, double max, Pageable pageable);

    @Query("select o from Product o where o.status=true and o.price between ?1 and ?2 order by o.createdate DESC")
    List<Product> findByPriceBetweenOrderByCreatedate(double min, double max);

    @Query("select o from Product  o where o.status=true and o.brand.id like ?1 order by o.createdate DESC")
    Page<Product> findByBrandOrderByCreatedateDesc(String brand, Pageable pageable);

    @Query("select o from Product  o where o.status=true and o.brand.id like ?1 order by o.createdate DESC")
    List<Product> findByBrandOrderByCreatedateDesc(String brand);

    @Query("select o from Product  o where o.status=true and  o.name like  ?1  order by  o.createdate DESC")
    Page<Product> findByNameOrderByCreatedateDesc(String name, Pageable pageable);

    @Query("select o from Product  o where o.status=true and  o.name like  ?1 order by  o.createdate DESC")
    List<Product> findByNameOrderByCreatedateDesc(String name);

    @Query("select o from Product o where o.status=true and o.category.id like ?1 order by o.rate DESC")
    Page<Product> findByCategoryOrderByRate(String keyword, Pageable pageable);

    @Query("select o from Product o where o.status = true")
    List<Product> findAllSort(Sort sort);
}
