package com.example.watermelon.repository;

import com.example.watermelon.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("select  o from OrderDetail o where o.order.id = ?1")
    public Page<OrderDetail> findOrderDetailByOrderId(long id , Pageable pageable);
    @Query("select  o from OrderDetail o where o.order.id = ?1")
    public List<OrderDetail> findOrderDetailByOrderId(long id);
}
