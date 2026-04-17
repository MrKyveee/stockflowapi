package com.kyvee.stockflowapi.repository;

import com.kyvee.stockflowapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<User, String> {
    User findByEmail(String email);
}
