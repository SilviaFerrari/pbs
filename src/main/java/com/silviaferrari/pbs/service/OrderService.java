package com.silviaferrari.pbs.service;

import com.silviaferrari.pbs.model.CartItem;
import com.silviaferrari.pbs.model.Order;
import com.silviaferrari.pbs.model.OrderItem;
import com.silviaferrari.pbs.model.User;
import com.silviaferrari.pbs.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato con id: " + id));
    }

    public Order createOrder(User user, List<CartItem> cartItems) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setStatus("IN_PROGRESS");

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            OrderItem item = new OrderItem();
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(
                    cartItem.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
            );
            item.setOrder(order);
            items.add(item);
            total = total.add(item.getPrice());
        }

        order.setItems(items);
        order.setTotal(total);

        return orderRepository.save(order);
    }


    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato"));
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
}