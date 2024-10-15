package com.example.demo.repositories;

import com.example.demo.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findProductsByBuyPriceBetweenOrderByBuyPriceDesc(BigDecimal lower, BigDecimal upper);

    List<Product> findByProductNameContainingOrProductCodeContainingOrProductDescriptionContaining(String productName, String productCode, String productDescription);

    @Query("SELECT p FROM Product p WHERE " +
            "p.productName LIKE %:keyword% OR " +
            "p.productCode LIKE %:keyword% OR " +
            "p.productDescription LIKE %:keyword%")
    List<Product> findProductsByKeyword(String keyword);

    @Query("""
            select p from Product p where p.productCode like ?1 
            or p.productName like ?1
            or p.productCode like ?1
            or p.productDescription like ?1
            """)
    List<Product> findByAnyContents(String text);

}

