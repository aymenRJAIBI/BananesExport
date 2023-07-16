package com.exportBanane.dto;

import com.exportBanane.models.Order;
import com.exportBanane.models.Recipient;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDto {

    private Integer id;

    @NotNull(message = "Creation date must not be empty")
    private LocalDate deliveryDate;

    @NotNull(message = "The quantity must not be empty")
    @Min(value = 25, message = "The quantity must be between 25 and 10000, and must be a multiple of 25")
    @Max(value = 10001, message = "The quantity must be between 25 and 10000, and must be a multiple of 25")
    private Integer quantity;

    @NotNull(message = "the recipient ID must not be empty")
    private Integer idRecipient;


    public static OrderDto fromEntity(Order order){

        return  OrderDto.builder()
                .id(order.getId())
                .deliveryDate(order.getDeliveryDate())
                .quantity(order.getQuantity())
                .idRecipient(order.getRecipient().getId()).build();
    }

    public static Order toEntity(OrderDto orderDto){

        return  Order.builder()
                .id(orderDto.getId())
                .deliveryDate(orderDto.getDeliveryDate())
                .quantity(orderDto.getQuantity())
                .recipient(Recipient.builder().id(orderDto.getIdRecipient()).build())
                .build();
    }




}
