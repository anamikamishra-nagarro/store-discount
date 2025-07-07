package com.retail.discount.entity;

import com.retail.discount.enums.Category;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private String name;
    private double price;
    private Category category;

}
