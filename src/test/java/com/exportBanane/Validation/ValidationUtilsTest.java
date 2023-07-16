package com.exportBanane.Validation;

import com.exportBanane.Service.impl.OrderServiceImpl;
import com.exportBanane.utils.OrderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidationUtilsTest {

    @Autowired
    OrderServiceImpl service;

    @Test
    public void isValidDeliveryDateTest() {

        LocalDate validDeliveryDate = LocalDate.now().plusWeeks(1);
        assertTrue(OrderUtil.isValidDeliveryDate(validDeliveryDate));

        LocalDate today = LocalDate.now();
        assertFalse(OrderUtil.isValidDeliveryDate(today));

        LocalDate pastDate = LocalDate.now().minusWeeks(1);
        assertFalse(OrderUtil.isValidDeliveryDate(pastDate));
    }

    @Test
    public void isValidQuantityTest() {

        Integer validQuantity = 100;
        assertTrue(OrderUtil.isValidQuantity(validQuantity));

        Integer invalidQuantity = 37;
        assertFalse(OrderUtil.isValidQuantity(invalidQuantity));

        Integer zeroQuantity = 0;
        assertTrue(OrderUtil.isValidQuantity(zeroQuantity));
    }

    @Test
    public void CalculatePrice (){

        Integer quantity = 100;
        Double price = 2.5;

        Double exceptedResult = quantity * price;
        Double actualResult = service.calculatePrice(quantity);
        assertEquals(exceptedResult, actualResult);

    }
}
