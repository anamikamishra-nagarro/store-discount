package com.retail.discount.entity;

import com.retail.discount.enums.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {
    private User user;
    private List<Product> products;
}
