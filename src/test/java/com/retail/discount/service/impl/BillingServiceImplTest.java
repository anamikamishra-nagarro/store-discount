package com.retail.discount.service.impl;


import com.retail.discount.entity.Bill;
import com.retail.discount.entity.Product;
import com.retail.discount.entity.User;
import com.retail.discount.enums.Category;
import com.retail.discount.enums.UserType;
import com.retail.discount.service.DiscountService;
import com.retail.discount.service.impl.BillingServiceImpl;
import com.retail.discount.utils.RetailUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingServiceImplTest {
    @Mock
    private DiscountService discountService;

    @Mock
    private RetailUtil retailUtil;

    @InjectMocks
    private BillingServiceImpl billingService;

    @Test
    void testCalculateNetPayableAmount() {
        User user = new User("Anamika", UserType.EMPLOYEE, LocalDate.of(2022, 1, 1));
        List<Product> products = List.of(new Product("Shampoo", 250.0, Category.OTHERS), new Product("Apple", 5.0, Category.GROCERIES) );
        Bill bill = new Bill(user, products);

        when(retailUtil.getTotalAmount(bill)).thenReturn(255.0);
        when(discountService.calculatePercentageDiscount(bill)).thenReturn(75.0);
        when(discountService.calculateFlatDiscount(180.0)).thenReturn(5.0);

        double result = billingService.calculateNetPayableAmount(bill);
        assertEquals(175.0, result);
    }
}
