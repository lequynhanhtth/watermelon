package com.example.watermelon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "image1")
    private String image1;
    @Column(name = "image2")
    private String image2;
    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createdate;
    @Column(name = "rate")
    private int rate;
    @Column(name = "quantity")
    private int quantity;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> list;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brandid")
    private Brand brand;
    @Column(name = "status")
    public Boolean status;

    public Product(Long id, String name, double price, String image1, String image2, Date createdate, int rate, int quantity, String categoryid, String brandid, Boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image1 = image1;
        this.image2 = image2;
        this.createdate = createdate;
        this.rate = rate;
        this.quantity = quantity;
        this.category.setId(categoryid);
        this.brand.setId(brandid);
        this.status = status;
    }
}
