package com.silviaferrari.pbs.repository;

import com.silviaferrari.pbs.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }