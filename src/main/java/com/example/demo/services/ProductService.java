package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;


    public List<Product> findAll() {
        return repository.findAll();
    }

    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Product> findByAnyContents(String contents) {
        return repository.findByAnyContents("%" + contents + "%");
    }

    public List<Product> findByPriceBetween(BigDecimal lower, BigDecimal upper) {
        if(lower.compareTo(upper) > 0) {
            BigDecimal temp = lower;
            lower = upper;
            upper = lower;
        }
        return repository.findProductsByBuyPriceBetweenOrderByBuyPriceDesc(lower, upper);
    }

}
