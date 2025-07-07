package com.retail.discount.service.impl;

import com.retail.discount.entity.Bill;
import com.retail.discount.entity.User;
import com.retail.discount.enums.UserType;
import com.retail.discount.service.DiscountService;
import com.retail.discount.utils.RetailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    RetailUtil retailUtil;
    @Override
    public double calculatePercentageDiscount(Bill bill) {
        double nonGroceryTotal = retailUtil.getNonGroceriesAmount(bill);
        User user=bill.getUser();
        UserType userType=user.getUserType();
        if(userType == UserType.EMPLOYEE)
                return nonGroceryTotal * 0.30;
        else if(userType == UserType.AFFILIATE)
                return nonGroceryTotal * 0.10;
        else if(userType == UserType.CUSTOMER && isLoyalCustomer(user))
                    return nonGroceryTotal * 0.05;

        return 0.0;
        }

    @Override
    public double calculateFlatDiscount(double amountAfterPercentageDisc) {

         int i= (int) (amountAfterPercentageDisc/100);
         return i*5;
    }

    public boolean isLoyalCustomer(User user) {
        LocalDate regDate=user.getRegistrationDate();
        return regDate.isBefore(LocalDate.now().minusYears(2));
    }

}
