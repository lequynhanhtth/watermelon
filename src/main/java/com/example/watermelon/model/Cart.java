package com.example.watermelon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart
{
    private Long id ;
    private Product product;
    private int quantity;
    private String size;
}
