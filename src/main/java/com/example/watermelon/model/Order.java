package com.example.watermelon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total")
    private double total;
    @Column(name = "address")
    @NotEmpty(message = "Không để trống địa chỉ")
    private String address;
    @Column(name = "note")
    private String note;
    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createdate;
    @Column(name = "status")
    private String status;
    @Column(name = "phonenumber")
    private String phonenumber;
    @Column(name = "Email")
    @NotEmpty(message = "Không để trống Email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "userid")
    private Account account;
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
