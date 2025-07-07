package com.retail.discount.utils;

import com.retail.discount.entity.Bill;
import com.retail.discount.entity.Product;
import com.retail.discount.enums.Category;
import org.springframework.stereotype.Component;

@Component
public class RetailUtil {
    public double getTotalAmount(Bill bill) {
        double total= bill.getProducts().stream().mapToDouble(Product::getPrice).sum();
        return total;
    }
    public double getGroceriesAmount(Bill bill) {
        double grocery= bill.getProducts().stream()
                .filter(p -> p.getCategory() == Category.GROCERIES)
                .mapToDouble(Product::getPrice).sum();
        return grocery;
    }
    public double getNonGroceriesAmount(Bill bill)
    {
        double nonGrocery= getTotalAmount(bill) - getGroceriesAmount(bill);
        return nonGrocery;
    }
}
