package com.kyvee.stockflowapi.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtils {

    // valida o slug e o formata
    public String validateAndFormatSlug(String slug) {

        if (slug == null || slug.isBlank()) throw new RuntimeException("slug cannot be empty");

        return slug
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "-");
    }

    // valida o email e o retorna
    public String validateEmail(String email) {
        // regex para validar formato de email
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,3}$";

        if (email == null || !email.matches(emailRegex)) {
            throw new RuntimeException("invalid email");
        }

        return email.trim().toLowerCase();
    }

    // valida se a senha tem pelo menos 8 caracteres, uma letra maiuscula e um numero
    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";

        return password != null && password.matches(passwordRegex);
    }
}
