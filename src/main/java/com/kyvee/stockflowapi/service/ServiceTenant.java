package com.kyvee.stockflowapi.service;

import com.kyvee.stockflowapi.entity.Tenant;
import com.kyvee.stockflowapi.repository.RepositoryTenant;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceTenant {

    private final RepositoryTenant repository;

    //------------------------------------------------------------------------------------------------------------------------------------------
    // CREATE
    //------------------------------------------------------------------------------------------------------------------------------------------
    public void saveTenant(Tenant tenant) {

        // verifica se o objeto e nulo antes de qualquer operacao
        if (tenant == null) {
            throw new RuntimeException();
        }

        // valida se o nome e nulo ou esta em branco
        if (tenant.getName() == null || tenant.getName().isBlank()) {
            throw new RuntimeException();
        }

        // valida se o cnpj e nulo ou se o formato e invalido
        tenant.setCnpj(validateCnpj(tenant.getCnpj()));

        // valida se o slug e nulo e o formata
        tenant.setSlug(validateAndFormatSlug(tenant.getSlug()));

        // valida se o email e nulo ou se o formato e invalido
        tenant.setEmail(validateEmail(tenant.getEmail()));

        // valida se o telefone e nulo ou se o formato e invalido
        tenant.setPhone(validatePhoneNumber(tenant.getPhone()));

        // valida se o endereco e nulo ou se o conteudo e insuficiente
        tenant.setAddress(validateAddress(tenant.getAddress()));

        repository.save(tenant);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    // READ
    //------------------------------------------------------------------------------------------------------------------------------------------
    public Tenant findBySlug(String slug) {

        String normalizedSlug = validateAndFormatSlug(slug);

        return repository.findBySlug(normalizedSlug);
    }

    public Tenant findByCnpj(String cnpj) {

        String normalizedCnpj = validateCnpj(cnpj);

        return repository.findByCnpj(normalizedCnpj);
    }


    //------------------------------------------------------------------------------------------------------------------------------------------
    // UPDATE
    //------------------------------------------------------------------------------------------------------------------------------------------
    // segui a recomendacao de uma ia para pesquisar sobre dirty checking e decidi
    // incluir o transactional pois nao sabia que o hibernate sincronizava o banco sozinho
    @Transactional
    public void updateTenant(String slug, Tenant tenant) {

        // limpa o slug antes da busca e verifica se o tenant existe no banco
        Tenant slugTenant = repository.findBySlug(validateAndFormatSlug(slug));

        if (slugTenant == null) {
            throw new RuntimeException();
        }

        if (tenant.getName() != null && !tenant.getName().isBlank()) {
            slugTenant.setName(tenant.getName());
        }

        if (tenant.getEmail() != null) {
            slugTenant.setEmail(validateEmail(tenant.getEmail()));
        }

        if (tenant.getPhone() != null) {
            slugTenant.setPhone(validatePhoneNumber(tenant.getPhone()));
        }

        if (tenant.getAddress() != null) {
            slugTenant.setAddress(validateAddress(tenant.getAddress()));
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    // DELETE
    //------------------------------------------------------------------------------------------------------------------------------------------
    public void deleteTenant(String cnpj) {

        String normalizedCnpj = validateCnpj(cnpj);

        repository.deleteByCnpj(normalizedCnpj);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    // UTILITIES
    //------------------------------------------------------------------------------------------------------------------------------------------
    private String validateCnpj(String rawCnpj) {
        // valida nulo ou vazio para o cnpj
        if (rawCnpj == null || rawCnpj.isBlank()) {
            throw new RuntimeException("cnpj cannot be empty");
        }

        // limpa a string deixando apenas numeros e letras
        String cnpj = rawCnpj.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        // verifica tamanho e se sao todos os digitos repetidos
        if (cnpj.length() != 14 || cnpj.matches("(\\w)\\1{13}")) {
            throw new RuntimeException("invalid cnpj format");
        }

        // calculo do primeiro digito verificador
        int sum1 = 0;
        int weight1 = 2;
        for (int i = 11; i >= 0; i--) {
            int num = (int) (cnpj.charAt(i) - 48);
            sum1 += num * weight1;
            weight1 = (weight1 == 9) ? 2 : weight1 + 1;
        }

        int mod1 = sum1 % 11;
        char digit13 = (mod1 < 2) ? '0' : (char) ((11 - mod1) + 48);

        // calculo do segundo digito verificador
        int sum2 = 0;
        int weight2 = 2;
        for (int i = 12; i >= 0; i--) {
            int num = (int) (cnpj.charAt(i) - 48);
            sum2 += num * weight2;
            weight2 = (weight2 == 9) ? 2 : weight2 + 1;
        }

        int mod2 = sum2 % 11;
        char digit14 = (mod2 < 2) ? '0' : (char) ((11 - mod2) + 48);

        if (!(digit13 == cnpj.charAt(12) && digit14 == cnpj.charAt(13))) {
            throw new RuntimeException("invalid cnpj");
        }

        return cnpj;
    }

    public String validateEmail(String email) {
        // regex para validar formato de email
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,3}$";

        if (email == null || !email.matches(emailRegex)) {
            throw new RuntimeException("invalid email");
        }

        return email.trim().toLowerCase();
    }

    public String validatePhoneNumber(String phone) {
        // regex para validar telefone fixo ou celular com ddd
        String phoneRegex = "^\\(?[1-9]{2}\\)?\\s?(?:[2-8]|9)[0-9]{3,4}\\-?[0-9]{4}$";

        if (phone == null || !phone.matches(phoneRegex)) {
            throw new RuntimeException("invalid phone number");
        }

        return phone.replaceAll("[^0-9]", "");
    }

    public String validateAddress(String address) {
        // valida tamanho minimo e presenca de ao menos um numero no endereco
        String addressRegex = "^.{10,}$";

        if (address == null || !address.matches(addressRegex) || !address.matches(".*\\d.*")) {
            throw new RuntimeException("invalid address");
        }

        return address.trim();
    }

    // valida o slug e o formata
    public String validateAndFormatSlug(String slug) {

        if (slug == null || slug.isBlank()) throw new RuntimeException("slug cannot be empty");

        return slug
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "-");
    }
}