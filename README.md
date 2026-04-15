# 📦 StockFlow API

> Uma API RESTful de gerenciamento de estoque multiempresa, construída com Spring Boot, Spring Security e JWT.

---

## 📌 O que é o projeto?

O **StockFlow** é uma API de warehouse (armazém) onde múltiplas empresas podem cadastrar produtos, movimentar estoque e acompanhar relatórios de forma totalmente isolada entre si. Cada empresa enxerga apenas os próprios dados — como se tivesse uma instância exclusiva do sistema.

---

## 🎯 Para que ele serve?

- Cadastrar empresas e seus usuários com diferentes níveis de acesso
- Autenticar usuários com JWT contendo o contexto da empresa (tenant)
- Gerenciar produtos e categorias por empresa
- Registrar entradas e saídas de estoque com rastreabilidade completa
- Consultar posição atual do estoque e histórico de movimentações
- Gerar relatórios de estoque mínimo, giro de produtos e movimentações por período
- Evoluir com novas camadas de segurança e regras de negócio ao longo das semanas

---

## 🗓️ Evolução Semanal

| Semana | Foco |
|--------|------|
| 1 | Setup do projeto, modelagem das entidades, relacionamentos e migrations |
| 2 | Cadastro de empresa e usuário, login com geração de JWT contendo o tenant |
| 3 | Filtro JWT customizado, proteção de endpoints, `SecurityFilterChain` |
| 4 | Isolamento multitenancy: garantir que toda query filtre pelo tenant do token |
| 5 | CRUD de Categories e Products com controle de propriedade por empresa |
| 6 | Roles internas: `OWNER`, `MANAGER`, `OPERATOR` com `@PreAuthorize` |
| 7 | StockMovement: registrar entradas e saídas com validação de estoque negativo |
| 8 | Refresh Token: geração, rotação e revogação |
| 9 | StockAlert: geração automática ao atingir estoque mínimo |
| 10 | Relatórios: posição atual, histórico por período, produtos críticos |
| 11 | AuditLog: interceptação automática de ações sensíveis |
| 12+ | Melhorias: rate limiting, blacklist de tokens, exportação de relatórios em CSV/PDF |

---

## 🚀 Como começar

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/stockflow-api.git

# Configure as variáveis de ambiente
# DB_URL, DB_USERNAME, DB_PASSWORD, JWT_SECRET, JWT_EXPIRATION

# Rode o projeto
./mvnw spring-boot:run

# Acesse a documentação
http://localhost:8080/swagger-ui.html
```

---

*Projeto desenvolvido com fins educacionais para aprendizado de Spring Security, JWT e arquitetura multitenancy.*
