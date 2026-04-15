ALTER TABLE user
    ADD CONSTRAINT fk_user_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenant (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_user_role
        FOREIGN KEY (role_id)
        REFERENCES role (id) ON DELETE RESTRICT;

ALTER TABLE refresh_token
    ADD CONSTRAINT fk_refresh_token_user
        FOREIGN KEY (user_id)
            REFERENCES user (id) ON DELETE CASCADE;

ALTER TABLE products
    ADD CONSTRAINT fk_products_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenant (id) ON DELETE CASCADE;

ALTER TABLE supplier
    ADD CONSTRAINT fk_supplier_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenant (id) ON DELETE CASCADE;

ALTER TABLE stock_movements
    ADD COLUMN tenant_id VARCHAR(36) NOT NULL,
    ADD COLUMN product_id VARCHAR(36) NOT NULL,
    ADD CONSTRAINT fk_movements_tenant
        FOREIGN KEY (tenant_id)
        REFERENCES tenant (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_movements_product
        FOREIGN KEY (product_id)
        REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE stock_alerts
    ADD CONSTRAINT fk_stock_alerts_product
        FOREIGN KEY (product_id)
            REFERENCES products (id) ON DELETE CASCADE;

ALTER TABLE audit_logs
    ADD CONSTRAINT fk_audit_logs_tenant
        FOREIGN KEY (tenant_id)
            REFERENCES tenant (id) ON DELETE CASCADE;

ALTER TABLE product_category
    ADD CONSTRAINT fk_pc_product
        FOREIGN KEY (product_id)
            REFERENCES products (id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_pc_category
        FOREIGN KEY (category_id)
        REFERENCES category (id) ON DELETE CASCADE;