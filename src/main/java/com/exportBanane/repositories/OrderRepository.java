package com.exportBanane.repositories;

import com.exportBanane.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByRecipientId(Integer recipientId);

}
