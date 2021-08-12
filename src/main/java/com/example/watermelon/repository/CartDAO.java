package com.example.watermelon.repository;

import com.example.watermelon.model.Cart;
import com.example.watermelon.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    public static List<Cart> Carts = new ArrayList<>();

    public static void addCart(long id, int quantity, Product product, String size) {
        if (Carts == null) {
            Cart cart = new Cart(id, product, quantity, size);
            Carts.add(cart);
        } else {
            boolean ok = false;
            for (Cart x : Carts) {
                if (x.getId() == id && x.getSize().equals(size)) {
                    x.setQuantity(x.getQuantity() + quantity);
                    ok = true;
                }
            }
            if (!ok) {
                Cart cart = new Cart(id, product, quantity, size);
                Carts.add(cart);
            }
        }
    }

    public static void update(long id, int quantity, String size) {
        for (Cart x : Carts) {
            if (x.getId() == id && x.getSize().equals(size)) {
                x.setQuantity(quantity);
            }
        }
    }

    public static void clearAll() {
        Carts.clear();
    }

    public static void delete(long id, String size) {
        for (int i = Carts.size() - 1; i >= 0; i--) {
            if (Carts.get(i).getId() == id && Carts.get(i).getSize().equals(size)) {
                Carts.remove(i);
            }
        }
    }

    public static double gettoltal() {
        double tong = 0;
        for (Cart x : Carts) {
            tong += x.getQuantity() * x.getProduct().getPrice();
        }
        return tong;
    }
}
