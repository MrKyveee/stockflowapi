CREATE TABLE tenant (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    cnpj VARCHAR(14), -- Removido NOT NULL conforme seu comentário de lógica futura para CPF
    email VARCHAR(250) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    address TEXT NOT NULL,
    -- "Não vou optar por colocar o endereço (address) composto por dar muito trabalho em um pequeno projeto"
    active BOOLEAN DEFAULT true,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- TODO: No futuro, permitir o cadastro via CPF para abranger profissionais liberais.
-- Objetivo: Permitir que o sistema atenda desde pequenos prestadores de serviços até empresas maiores.

CREATE TABLE user (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    active BOOLEAN DEFAULT true,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE role (
    id VARCHAR(36) PRIMARY KEY,
    name ENUM('OWNER', 'MANAGER', 'OPERATOR') NOT NULL
);

CREATE TABLE refresh_token (
    id VARCHAR(36) PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    expiresAt TIMESTAMP NOT NULL,
    revoked BOOLEAN DEFAULT false,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE category (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description TEXT,
    active BOOLEAN DEFAULT true
);

CREATE TABLE products (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    sku VARCHAR(50) UNIQUE,
    unit VARCHAR(10) NOT NULL,
    currentStock DECIMAL(15,3) DEFAULT 0.000,
    minimumStock DECIMAL(15,3) DEFAULT 0.000,
    active BOOLEAN DEFAULT true,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE supplier (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    active BOOLEAN DEFAULT true,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stock_movements (
    id VARCHAR(36) PRIMARY KEY,
    type ENUM('IN', 'OUT', 'REVERSAL') NOT NULL,
    quantity DECIMAL(15,3) NOT NULL,
    reason VARCHAR(255),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stock_alerts (
    id VARCHAR(36) PRIMARY KEY,
    message TEXT NOT NULL,
    resolved BOOLEAN DEFAULT false,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    resolvedAt TIMESTAMP
);

CREATE TABLE audit_logs (
    id VARCHAR(36) PRIMARY KEY,
    action VARCHAR(100) NOT NULL,
    entity VARCHAR(100) NOT NULL,
    entityId VARCHAR(36) NOT NULL,
    performedBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
