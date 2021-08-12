package com.example.watermelon.controller;

import com.example.watermelon.model.Brand;
import com.example.watermelon.model.Product;
import com.example.watermelon.page.PageProduct;
import com.example.watermelon.service.BrandService;
import com.example.watermelon.service.ProductService;
import com.example.watermelon.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class productPageController {
    @Autowired
    ProductService productService;
    @Autowired
    SessionService sessionService;
    @Autowired
    BrandService brandService;

    @RequestMapping("/home/product")
    public String showProduct(@RequestParam("p") Optional<Integer> p, Model model) {
        if (sessionService.get("pageProducts") == null) {
            Pageable pageable = PageRequest.of(p.orElse(1) - 1, 9);
            Page<Product> page = productService.findAllProductOrderByCreatedate(pageable);
            model.addAttribute("pageProducts", page);
        } else {
            PageProduct pageProduct = sessionService.get("pageProducts");
            pageProduct.getList(p.orElse(1) - 1, 9);
            model.addAttribute("pageProducts", pageProduct);
        }
        return "home/homecontent/product";
    }

    @RequestMapping("/home/product/sort")
    public String showProduct(@RequestParam("max-value") double max, @RequestParam("min-value") double min, Model model) {
        if (sessionService.get("pageProducts") == null) {
            Pageable pageable = PageRequest.of(0, 9);
            Page<Product> page = productService.findByPriceBetweenOrderByCreatedate(min, max, pageable);
            List<Product> lists = productService.findByPriceBetweenOrderByCreatedate(min, max);
            if (lists.size() > 0) {
                PageProduct pageProduct = new PageProduct(0, 9, lists);
                sessionService.set("pageProducts", pageProduct);
            }
            model.addAttribute("pageProducts", page);
        } else {
            PageProduct pageProduct = sessionService.get("pageProducts");
            List<Product> products = pageProduct.getLists();
            List<Product> list = new ArrayList<>();
            for (Product x : products) {
                if (x.getPrice() >= min && x.getPrice() <= max) {
                    list.add(x);
                }
            }
            pageProduct.setTotalPages((int) Math.ceil((double) list.size() / (double) pageProduct.getElement()));
            pageProduct.setLists(list);
            pageProduct.getList(0, 9);
            sessionService.set("pageProducts", pageProduct);
            model.addAttribute("pageProducts", pageProduct);
        }
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        return "home/homecontent/product";
    }

    @RequestMapping("/home/product/brandsort")
    public String showProduct(@RequestParam("brand") String brand, Model model) {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Product> page = productService.findByBrandOrderByCreatedate(brand, pageable);
        List<Product> lists = productService.findByBrandOrderByCreatedate(brand);
        if (lists.size() > 0) {
            PageProduct pageProduct = new PageProduct(0, 9, lists);
            sessionService.set("pageProducts", pageProduct);
        }
        model.addAttribute("pageProducts", page);
        return "home/homecontent/product";
    }

    @RequestMapping("/home/product/sortprice")
    public String sortPrice(@RequestParam("field") String field, Model model) {
        if (sessionService.get("pageProducts") == null) {
            Sort sort;
            if (field.equals("price1")) {
                sort = Sort.by(Sort.Direction.DESC, "price");
            } else if (field.equals("price2")) {
                sort = Sort.by(Sort.Direction.ASC, "price");
            } else {
                sort = Sort.by(Sort.Direction.DESC, field);
            }
            List<Product> products = productService.findAllSort(sort);
            if (products != null) {
                PageProduct pageProduct = new PageProduct(0, 9, products);
                sessionService.set("pageProducts", pageProduct);
                model.addAttribute("pageProducts", pageProduct);
            }
        } else {
            PageProduct pageProduct = sessionService.get("pageProducts");
            List<Product> products = pageProduct.getLists();
            if (field.equals("price1")) {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getPrice() > o2.getPrice() ? 1 : -1;
                    }
                });
            } else if (field.equals("price2")) {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getPrice() < o2.getPrice() ? 1 : -1;
                    }
                });
            } else if (field.equals("createdate")) {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o2.getCreatedate().compareTo(o1.getCreatedate());
                    }
                });
            } else {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getRate() > o2.getRate() ? 1 : -1;
                    }
                });
            }
            pageProduct.setLists(products);
            pageProduct.setTotalPages((int) Math.ceil((double) products.size() / (double) pageProduct.getElement()));
            pageProduct.getList(0, 9);
            sessionService.set("pageProducts", pageProduct);
            model.addAttribute("pageProducts", pageProduct);
            model.addAttribute("field", field);
        }
        return "home/homecontent/product";
    }

    @RequestMapping("/home/product/search")
    public String getNames(@RequestParam("search") Optional<String> keywords, Model model) {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Product> page = productService.findByNameOrderbyCreatedate("%" + keywords.orElse("") + "%", pageable);
        List<Product> lists = productService.findByNameOrderbyCreatedate("%" + keywords.orElse("") + "%");
        if (lists.size() > 0) {
            PageProduct pageProduct = new PageProduct(0, 9, lists);
            sessionService.set("pageProducts", pageProduct);
        }
        model.addAttribute("pageProducts", page);
        return "home/homecontent/product";
    }

    @ModelAttribute("brandList")
    public List<Brand> getBrand() {
        return brandService.findAll();
    }

}
