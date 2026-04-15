package com.kyvee.stockflowapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ProductCategoryId implements Serializable {

    @Column(columnDefinition = "VARCHAR(36)", nullable = false)
    private String productId;

    @Column(columnDefinition = "VARCHAR(36)", nullable = false)
    private String categoryId;
}