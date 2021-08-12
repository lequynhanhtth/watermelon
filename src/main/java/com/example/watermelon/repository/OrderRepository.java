package com.example.watermelon.repository;

import com.example.watermelon.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.account.id = ?1")
    public Page<Order> findOrderByAccountId(long id, Pageable pageable);

}
