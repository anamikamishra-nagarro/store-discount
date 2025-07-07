package com.retail.discount.service;

import com.retail.discount.entity.Bill;
import org.springframework.stereotype.Service;

@Service
public interface DiscountService {
    double calculatePercentageDiscount(Bill bill);
    double calculateFlatDiscount(double amountAfterPercentageDisc);
}
