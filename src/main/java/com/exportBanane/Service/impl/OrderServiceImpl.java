package com.exportBanane.Service.impl;

import com.exportBanane.Service.OrderService;
import com.exportBanane.dto.OrderDto;
import com.exportBanane.exceptions.OrdreNoPermittedException;
import com.exportBanane.models.Order;
import com.exportBanane.models.Recipient;
import com.exportBanane.repositories.OrderRepository;
import com.exportBanane.repositories.RecipientRepository;
import com.exportBanane.utils.OrderUtil;
import com.exportBanane.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final RecipientRepository recipRepository;
    private final ObjectsValidator<OrderDto> validator;
    @Value("${app.unitPrice}")
    private Double unitPrice;

    @Override
    public Integer save(OrderDto dto) {

        validator.validate(dto);
        Order order = OrderDto.toEntity(dto);
        order.setCreatedDate(LocalDate.now());

        if (!OrderUtil.isValidDeliveryDate(order.getDeliveryDate())) {
            throw new OrdreNoPermittedException("Delivery date must be at least one week in the future", "save operation", "OrderServiceImpl");
        }
        if (!OrderUtil.isValidQuantity(order.getQuantity())) {
            throw new OrdreNoPermittedException("Not valid Quantity,the quantity must be a multiple of 25", "save operation", "OrderServiceImpl");
        }
        Double orderPrice = calculatePrice(dto.getQuantity());
        order.setPrice(orderPrice);

        Recipient recipient = recipRepository.findById(order.getRecipient().getId()).orElseThrow(() -> new EntityNotFoundException("Recipient not found with the ID: " + +order.getRecipient().getId()));

        order.setRecipient(recipient);
        return repository.save(order).getId();
    }

    @Override
    public List<OrderDto> findAll() {
        return repository.findAll().stream().map(OrderDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findByRecipientId(Integer id) {
        List<OrderDto> orders;
        try {
            orders = repository.findByRecipientId(id).stream().map(OrderDto::fromEntity).collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Order not found with the ID: " + id);
        }
        return orders;
    }

    @Override
    public OrderDto findById(Integer id) {
        return repository.findById(id).map(OrderDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("Order not found with the ID : " + id));
    }

    @Override
    public Integer update(OrderDto dto) {
        validator.validate(dto);

        Order existingOrder = repository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Order not found with the ID : " + dto.getId()));
        existingOrder.setCreatedDate(LocalDate.now());
        if (!OrderUtil.isValidDeliveryDate(dto.getDeliveryDate())) {
            throw new OrdreNoPermittedException("Delivery date must be at least one week in the future", "update operation", "CommandeServiceImpl");
        }
        if (!OrderUtil.isValidQuantity(dto.getQuantity())) {
            throw new OrdreNoPermittedException("Not valid Quantity,the quantity must be a multiple of 25", "save operation", "CommandeServiceImpl");
        }
        existingOrder.setDeliveryDate(dto.getDeliveryDate());
        existingOrder.setQuantity(dto.getQuantity());

        Double orderPrice = calculatePrice(dto.getQuantity());
        existingOrder.setPrice(orderPrice);

        return repository.save(existingOrder).getId();
    }

    @Override
    public void delete(Integer id) {

        repository.deleteById(id);
    }

   public double calculatePrice(int quantity) {

        return unitPrice * quantity;
    }


}
