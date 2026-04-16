package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.StockAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryStockAlert extends JpaRepository<StockAlert, String> {
}
