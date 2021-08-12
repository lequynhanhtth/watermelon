package com.example.watermelon.service;

import com.example.watermelon.model.DashBoard;
import com.example.watermelon.model.Order;
import com.example.watermelon.model.productDashboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> findAll();

    public void save(Order order);

    public void delete(long id);

    public Optional<Order> findById(long id);
    public Page<Order> findOrderByAccountId(long id , Pageable pageable);
    public List<DashBoard> getDashBoard(int year);
    public List<productDashboard> getproductDB(int year);
    public Page<Order> findAll(Pageable pageable);

}
