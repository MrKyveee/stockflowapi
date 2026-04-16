package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRole extends JpaRepository<Role, String> {
}
