package com.example.watermelon.service;

import com.example.watermelon.model.OrderDetail;
import com.example.watermelon.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public Optional<OrderDetail> findById(long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public Page<OrderDetail> findOrderDetailByOrderId(long id, Pageable pageable) {
        return orderDetailRepository.findOrderDetailByOrderId(id,pageable);
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrderId(long id) {
        return orderDetailRepository.findOrderDetailByOrderId(id);
    }

}
