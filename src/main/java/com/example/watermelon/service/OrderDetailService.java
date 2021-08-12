package com.example.watermelon.service;

import com.example.watermelon.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    public List<OrderDetail> findAll();

    public void save(OrderDetail orderDetail);

    public void delete(long id);

    public Optional<OrderDetail> findById(long id);

    public Page<OrderDetail> findOrderDetailByOrderId(long name, Pageable pageable);

    public List<OrderDetail> findOrderDetailByOrderId(long name);
}
