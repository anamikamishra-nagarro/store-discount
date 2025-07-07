package com.retail.discount.controller;

import com.retail.discount.entity.Bill;
import com.retail.discount.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping("/calculate")
    public double calculateBill(@RequestBody Bill bill) {

        return billingService.calculateNetPayableAmount(bill);
    }
}

