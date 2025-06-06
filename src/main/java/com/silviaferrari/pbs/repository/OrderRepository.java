package com.silviaferrari.pbs.repository;

import com.silviaferrari.pbs.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }