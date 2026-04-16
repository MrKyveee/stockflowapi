package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryStockMovement extends JpaRepository<StockMovement, String> {
}
