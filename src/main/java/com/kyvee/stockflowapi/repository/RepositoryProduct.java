package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProduct extends JpaRepository<Product, String> {
}