package com.example.watermelon.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
@Data
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "total")
    private double total;
    @Column(name = "size")
    private String size;
    @ManyToOne
    @JoinColumn(name = "orderid")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    public OrderDetail(Long id, int quantity, double total, String size, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.size = size;
        this.order = order;
        this.product = product;
    }
}
