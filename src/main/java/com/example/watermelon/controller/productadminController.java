package com.example.watermelon.controller;

import com.example.watermelon.model.Brand;
import com.example.watermelon.model.Category;
import com.example.watermelon.model.Product;
import com.example.watermelon.service.BrandService;
import com.example.watermelon.service.CategoryService;
import com.example.watermelon.service.ParamService;
import com.example.watermelon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class productadminController {
    @Autowired
    ParamService paramService;
    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin/showaddproduct")
    public String showaddProduct() {
        return "home/admin/addnewProduct";
    }

    @RequestMapping("/admin/addproduct")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("photo1") MultipartFile photo1, @RequestParam("photo2") MultipartFile photo2) {
        if (!photo1.isEmpty()) {
            paramService.save(photo1, "/src/main/resources/static/images/" + product.getBrand().id);
            product.setImage1(photo1.getOriginalFilename());
            if (!photo2.isEmpty()) {
                product.setImage2(photo1.getOriginalFilename());
            }
        }
        if (!photo2.isEmpty()) {
            paramService.save(photo2, "/src/main/resources/static/images/" + product.getBrand().id);
            product.setImage2(photo2.getOriginalFilename());
        }

        product.setCreatedate(new Date());
        productService.save(product);
        return "home/admin/addnewProduct";
    }

    @RequestMapping("/admin/products")
    public String showProducts(Model model, @RequestParam("p") Optional<Integer> field) {
        Pageable pageable = PageRequest.of(field.orElse(1) - 1, 8);
        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("products", products);
        return "home/admin/productManager";
    }

    @RequestMapping("/admin/products/detail/{id}")
    public String showProductDetail(Model model, @PathVariable("id") long id, @RequestParam("p") Optional<Integer> field) {
        Pageable pageable = PageRequest.of(field.orElse(1) - 1, 8);
        Page<Product> products = productService.findAll(pageable);
        Product product = productService.findById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        model.addAttribute("show", true);
        return "home/admin/productManager";
    }

    @RequestMapping("/admin/product/save")
    public String showProductSave(Model model, @ModelAttribute("product") Product product, @RequestParam("createdatenone") String date, @RequestParam("photo1") MultipartFile photo1, @RequestParam("photo2") MultipartFile photo2) {
        if (!photo1.isEmpty()) {
            paramService.save(photo1, "/src/main/resources/static/images/" + product.getBrand().id);
            product.setImage1(photo1.getOriginalFilename());
        }
        if (!photo2.isEmpty()) {
            paramService.save(photo2, "/src/main/resources/static/images/" + product.getBrand().id);
            product.setImage2(photo2.getOriginalFilename());
        }
        Date createdate;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            createdate = simpleDateFormat.parse(date);
        } catch (Exception e) {
            createdate = null;
            throw new RuntimeException();
        }
        product.setCreatedate(createdate);
        productService.save(product);
        return "redirect:/admin/products/detail/" + product.getId();
    }

    @RequestMapping("/admin/product/delete/{id}")
    public String deleteProductadmin(@PathVariable("id") long id) {
        Product product = productService.findById(id).get();
        product.setStatus(false);
        productService.save(product);
        return "redirect:/admin/products/detail/" + product.getId();
    }

    @RequestMapping("/admin/product/undelete/{id}")
    public String undeleteProductadmin(@PathVariable("id") long id) {
        Product product = productService.findById(id).get();
        product.setStatus(true);
        productService.save(product);
        return "redirect:/admin/products/detail/" + product.getId();
    }


    @ModelAttribute("brands")
    public List<Brand> getbrands() {
        List<Brand> list = brandService.findAll();
        return list;
    }

    @ModelAttribute("categories")
    public List<Category> getcategories() {
        List<Category> list = categoryService.findAll();
        return list;
    }
}
