package com.example.watermelon.service;

import com.example.watermelon.model.DashBoard;
import com.example.watermelon.model.Order;
import com.example.watermelon.model.OrderDetail;
import com.example.watermelon.model.productDashboard;
import com.example.watermelon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Page<Order> findOrderByAccountId(long id, Pageable pageable) {
        return orderRepository.findOrderByAccountId(id, pageable);
    }

    @Override
    public List<DashBoard> getDashBoard(int year) {
        List<Order> list = orderRepository.findAll();
        List<DashBoard> dashBoardList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            dashBoardList.add(new DashBoard(i, 0, 0));
        }

        for (Order x : list) {
            if (x.getCreatedate().getYear() + 1900 == year) {
                for (DashBoard y : dashBoardList) {
                    if (y.getMonths() == x.getCreatedate().getMonth()+1) {
                        y.setTotal(y.getTotal() + x.getTotal());
                        y.setCount(y.getCount() + x.getOrderDetails().size());
                        break;
                    }
                }
            }
        }
        return dashBoardList;
    }

    @Override
    public List<productDashboard> getproductDB(int year) {
        List<Order> list = orderRepository.findAll();
        Map<String, Integer> mapP = new HashMap<>();
        for (Order x : list) {
            if (x.getCreatedate().getYear() + 1900 == year) {
                for (OrderDetail y : x.getOrderDetails()) {
                    if (mapP.get(y.getProduct().getName()) == null) {
                        mapP.put(y.getProduct().getName(), 1);
                    } else {
                        mapP.put(y.getProduct().getName(), mapP.get(y.getProduct().getName()) + 1);
                    }
                }
            }
        }
        List<productDashboard> listP = new ArrayList<>();
        for (String x : mapP.keySet()) {
            listP.add(new productDashboard(x, mapP.get(x)));
        }
        return listP;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }




}
