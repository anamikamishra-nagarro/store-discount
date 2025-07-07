package com.retail.discount.entity;

import com.retail.discount.enums.UserType;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private UserType userType;
    private LocalDate registrationDate;
}
