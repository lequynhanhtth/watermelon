package com.example.watermelon.page;

import com.example.watermelon.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PageProduct {
    private int number;
    private int totalPages;
    private int element;
    private List<Product> content;
    private List<Product> lists;

    public PageProduct(int number, int element, List<Product> list) {
        this.number = number;
        this.element = element;
        this.lists = list;
        this.totalPages = (int) Math.ceil((double) list.size() / (double) element);
        this.content = getList(list, number, element, this.totalPages);
    }

    public List<Product> getList(List<Product> list, int number, int element, int totalPages) {
        List<Product> products = new ArrayList<>();
        if (number + 1 == totalPages) {
            for (int i = (number) * element; i < list.size(); i++) {
                products.add(list.get(i));
            }
        } else {
            for (int i = (number) * element; i < (number + 1) * element; i++) {
                products.add(list.get(i));
            }
        }
        return products;
    }

    public void getList(int number, int element) {
        this.number = number;
        this.element = element;
        List<Product> products = new ArrayList<>();
        if (number + 1 == totalPages) {
            for (int i = (number) * element; i < lists.size(); i++) {
                products.add(lists.get(i));
            }
        } else {
            for (int i = (number) * element; i < (number + 1) * element; i++) {
                products.add(lists.get(i));
            }
        }
        this.content = products;
    }
}
