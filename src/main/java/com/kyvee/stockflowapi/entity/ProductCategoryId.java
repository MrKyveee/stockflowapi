package com.kyvee.stockflowapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProductCategoryId implements Serializable {

    @Column(name = "product_id")
    private String productId;

    @Column(name = "category_id")
    private String categoryId;
}