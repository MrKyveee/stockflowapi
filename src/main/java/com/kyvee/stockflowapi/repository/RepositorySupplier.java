package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorySupplier extends JpaRepository<Supplier, String> {
}
