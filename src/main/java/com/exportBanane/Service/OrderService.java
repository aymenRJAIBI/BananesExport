package com.exportBanane.Service;

import com.exportBanane.dto.OrderDto;

import java.util.List;

public interface OrderService extends AbstractService<OrderDto> {
    List<OrderDto> findByRecipientId(Integer recipientId);

     double calculatePrice(int quantity);

}
