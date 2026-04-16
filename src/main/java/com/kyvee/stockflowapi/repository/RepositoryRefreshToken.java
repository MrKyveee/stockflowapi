package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRefreshToken extends JpaRepository<RefreshToken, String> {
}
