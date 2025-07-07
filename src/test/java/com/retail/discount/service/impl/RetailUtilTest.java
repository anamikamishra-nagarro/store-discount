package com.retail.discount.service.impl;

import com.retail.discount.entity.Bill;
import com.retail.discount.entity.Product;
import com.retail.discount.entity.User;
import com.retail.discount.enums.Category;
import com.retail.discount.enums.UserType;
import com.retail.discount.utils.RetailUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RetailUtilTest {
    @InjectMocks
    private RetailUtil retailUtil;

    Bill bill;
    List<Product> products;
    @BeforeEach
    private void initialize(){
        User user = new User("Test User", UserType.CUSTOMER, LocalDate.of(2020, 1, 1));
        List<Product> products = List.of(
                new Product("Apple", 3.5, Category.GROCERIES),
                new Product("Shampoo", 250.0, Category.OTHERS),
                new Product("T-shirt", 500.0, Category.OTHERS)
        );

        bill = new Bill(user, products);
    }
    @Test
    void testGetTotalAmount() {
        double total = retailUtil.getTotalAmount(bill);
        assertEquals(753.5, total);
    }
    @Test
    void testGetGroceriesAmount() {
        double groceries = retailUtil.getGroceriesAmount(bill);
        assertEquals(3.5, groceries);
    }

    @Test
    void testGetNonGroceriesAmount() {
        double nonGroceries = retailUtil.getNonGroceriesAmount(bill);
        assertEquals(750.0, nonGroceries);
    }
}
