CREATE TABLE tenant (
    id          VARCHAR(36)  PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    cnpj        VARCHAR(14),
    email       VARCHAR(250) NOT NULL,
    phone       VARCHAR(11)  NOT NULL,
    address     TEXT         NOT NULL,
    active      BOOLEAN      DEFAULT true,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE role (
    id    VARCHAR(36) PRIMARY KEY,
    name  ENUM('OWNER', 'MANAGER', 'OPERATOR') NOT NULL
);

CREATE TABLE users (
    id          VARCHAR(36)  PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    email       VARCHAR(250) NOT NULL UNIQUE,
    password    VARCHAR(250) NOT NULL,
    active      BOOLEAN      DEFAULT true,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE refresh_token (
    id          VARCHAR(36)  PRIMARY KEY,
    token       VARCHAR(255) NOT NULL UNIQUE,
    expires_at  TIMESTAMP    NOT NULL,
    revoked     BOOLEAN      DEFAULT false,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE category (
    id          VARCHAR(36)  PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    description TEXT,
    active      BOOLEAN      DEFAULT true
);

CREATE TABLE products (
    id              VARCHAR(36)    PRIMARY KEY,
    name            VARCHAR(255)   NOT NULL,
    description     TEXT,
    sku             VARCHAR(50)    UNIQUE,
    unit            VARCHAR(10)    NOT NULL,
    current_stock   DECIMAL(15,3)  DEFAULT 0.000,
    minimum_stock   DECIMAL(15,3)  DEFAULT 0.000,
    active          BOOLEAN        DEFAULT true,
    created_at      TIMESTAMP      DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product_category (
    product_id   VARCHAR(36) NOT NULL,
    category_id  VARCHAR(36) NOT NULL,
    PRIMARY KEY (product_id, category_id)
);

CREATE TABLE supplier (
    id          VARCHAR(36)  PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    cnpj        VARCHAR(14),
    email       VARCHAR(250) NOT NULL,
    phone       VARCHAR(11),
    active      BOOLEAN      DEFAULT true,
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stock_movements (
    id          VARCHAR(36)   PRIMARY KEY,
    type        ENUM('IN', 'OUT', 'REVERSAL') NOT NULL,
    quantity    DECIMAL(15,3) NOT NULL,
    reason      VARCHAR(255),
    created_at  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stock_alerts (
    id           VARCHAR(36) PRIMARY KEY,
    product_id   VARCHAR(36) NOT NULL,
    message      TEXT        NOT NULL,
    resolved     BOOLEAN     DEFAULT false,
    created_at   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    resolved_at  TIMESTAMP
);

CREATE TABLE audit_logs (
    id            VARCHAR(36)  PRIMARY KEY,
    action        VARCHAR(100) NOT NULL,
    entity        VARCHAR(100) NOT NULL,
    entity_id     VARCHAR(36)  NOT NULL,
    performed_by  VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);
