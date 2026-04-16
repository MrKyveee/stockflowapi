package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategory extends JpaRepository<Category,String> {
}
