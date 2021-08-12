package com.example.watermelon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
    @Column(name = "name")
    public String name;
    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    List<Product> list;
    public Brand(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
