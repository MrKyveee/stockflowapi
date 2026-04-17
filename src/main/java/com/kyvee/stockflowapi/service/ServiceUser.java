package com.kyvee.stockflowapi.service;

import com.kyvee.stockflowapi.entity.User;
import com.kyvee.stockflowapi.repository.RepositoryUser;
import com.kyvee.stockflowapi.util.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceUser {

    private final RepositoryUser repository;

    //------------------------------------------------------------------------------------------------------------------------------------------
    // CREATE
    //------------------------------------------------------------------------------------------------------------------------------------------
    public void saveUser(User user) {

        if (user == null) {
            throw new RuntimeException();
        }

        if (user.getName() == null || user.getName().isBlank()) {
            throw new RuntimeException();
        }

        user.setEmail(ValidationUtils.validateEmail(user.getEmail()));

        if (!ValidationUtils.isValidPassword(user.getPassword())) {
            throw new RuntimeException();
        }

        //ajustes
        repository.save(user);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    // READ
    //------------------------------------------------------------------------------------------------------------------------------------------
    public User findByEmail(String email) {

        String normalizedEmail = ValidationUtils.validateEmail(email);

        return repository.findByEmail(email);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    // UPDATE
    //------------------------------------------------------------------------------------------------------------------------------------------
    @Transactional
    public void updateUser() {

    }
}
