package com.retail.discount.service.impl;

import com.retail.discount.entity.Bill;
import com.retail.discount.entity.Product;
import com.retail.discount.entity.User;
import com.retail.discount.enums.Category;
import com.retail.discount.enums.UserType;
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
public class DiscountServiceImplTest {
    @Mock
    private RetailUtil retailUtil;

    @InjectMocks
    private DiscountServiceImpl discountService;
    @Test
    public void testEmployeeDiscount() {
        User user = new User("Alice", UserType.EMPLOYEE, LocalDate.now().minusYears(1));
        List<Product> products = List.of(new Product("Shampoo", 270.0, Category.OTHERS), new Product("Apple", 5.0, Category.GROCERIES) );
        Bill bill = new Bill(user, products);

        when(retailUtil.getNonGroceriesAmount(bill)).thenReturn(270.0);

        double discount = discountService.calculatePercentageDiscount(bill);
        assertEquals(81.0, discount); // 30% of 300
    }
    @Test
    public void testLoyalCustomerDiscount() {
        User user = new User("Akash", UserType.CUSTOMER, LocalDate.now().minusYears(3));
        List<Product> products = List.of(new Product("Shampoo", 270.0, Category.OTHERS), new Product("Apple", 5.0, Category.GROCERIES) );
        Bill bill = new Bill(user, products);

        when(retailUtil.getNonGroceriesAmount(bill)).thenReturn(270.0);

        double discount = discountService.calculatePercentageDiscount(bill);
        assertEquals(13.5, discount); // 30% of 300
    }
    @Test
    public void testNotLoyalCustomerDiscount() {
        User user = new User("Akash", UserType.CUSTOMER, LocalDate.now().minusYears(1));
        List<Product> products = List.of(new Product("Shampoo", 270.0, Category.OTHERS), new Product("Apple", 5.0, Category.GROCERIES) );
        Bill bill = new Bill(user, products);

        when(retailUtil.getNonGroceriesAmount(bill)).thenReturn(270.0);

        double discount = discountService.calculatePercentageDiscount(bill);
        assertEquals(0.0, discount); // 30% of 300
    }
    @Test
    public void testAffiliateDiscount() {
        User user = new User("Akash", UserType.AFFILIATE, LocalDate.now().minusYears(3));
        List<Product> products = List.of(new Product("Shampoo", 270.0, Category.OTHERS), new Product("Apple", 5.0, Category.GROCERIES) );
        Bill bill = new Bill(user, products);

        when(retailUtil.getNonGroceriesAmount(bill)).thenReturn(270.0);

        double discount = discountService.calculatePercentageDiscount(bill);
        assertEquals(27.0, discount); // 30% of 300
    }
    @Test
    void testFlatDiscount() {
        assertEquals(15.0, discountService.calculateFlatDiscount(345.0));
        assertEquals(0.0, discountService.calculateFlatDiscount(99.9));
        assertEquals(25.0, discountService.calculateFlatDiscount(501.0));
    }
}
