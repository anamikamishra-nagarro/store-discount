package com.retail.discount.service;

import com.retail.discount.entity.Bill;
import org.springframework.stereotype.Service;

public interface BillingService {
    public double calculateNetPayableAmount(Bill bill);
}
