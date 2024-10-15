package com.example.demo.controllers;


import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;;

    @GetMapping("")
    public String getProducts(Model model) {
        model.addAttribute("products", service.findAll());

        return "product_list";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String searchParam, Model model) {
        List<Product> products = service.findByAnyContents(searchParam);

        model.addAttribute("products", products);
        return "product_list";
    }

    @GetMapping("/searchByPrice")
    public String searchProductsByPrice(@RequestParam(defaultValue = "10.0") BigDecimal lower,
                                        @RequestParam(defaultValue = "9999.0") BigDecimal upper, Model model) {

        List<Product> products = service.findByPriceBetween(lower, upper);

        model.addAttribute("products", products);
        model.addAttribute("lower", lower);
        model.addAttribute("upper", upper);

        return "product_list";
    }
}
