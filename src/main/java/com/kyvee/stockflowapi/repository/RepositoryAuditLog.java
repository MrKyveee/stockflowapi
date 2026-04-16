package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAuditLog extends JpaRepository<AuditLog, String> {
}
