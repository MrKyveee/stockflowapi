package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTenant extends JpaRepository<Tenant, String> {
}
