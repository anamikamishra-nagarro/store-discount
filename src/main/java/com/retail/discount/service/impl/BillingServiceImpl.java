package com.retail.discount.service.impl;

import com.retail.discount.entity.Bill;
import com.retail.discount.entity.Product;
import com.retail.discount.enums.Category;
import com.retail.discount.service.BillingService;
import com.retail.discount.service.DiscountService;
import com.retail.discount.utils.RetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    DiscountService discountService;

    @Autowired
    RetailUtil retailUtil;
    @Override
    public double calculateNetPayableAmount(Bill bill) {
        double totalAmount=retailUtil.getTotalAmount(bill);
        double percentageDiscount = discountService.calculatePercentageDiscount(bill);
        double flatDiscount= discountService.calculateFlatDiscount(totalAmount-percentageDiscount);
        return totalAmount-(percentageDiscount+flatDiscount);
    }
}
