package com.exportBanane.utils;

import java.time.LocalDate;

public  class OrderUtil {

    //verif date
    //verif prix

    public static boolean isValidDeliveryDate(LocalDate deliveryDate) {

        LocalDate todayDate = LocalDate.now();
        LocalDate dateMin = todayDate.plusWeeks(1);
        return deliveryDate.isAfter(dateMin) || deliveryDate.equals(dateMin);

    }

    public static boolean isValidQuantity(Integer quantity) {

        return quantity % 25 == 0;
    }
}
