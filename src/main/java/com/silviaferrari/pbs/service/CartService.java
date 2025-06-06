package com.silviaferrari.pbs.service;

import com.silviaferrari.pbs.model.CartItem;
import com.silviaferrari.pbs.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.*;

@Service
@SessionScope
public class CartService {
    private final Map<Long, CartItem> items = new HashMap<>();

    public void addProduct(Product product) {
        CartItem item = items.get(product.getId());
        if (item == null) {
            items.put(product.getId(), new CartItem(product, 1));
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(items.values());
    }

    public void removeProduct(Long productId) {
        items.remove(productId);
    }

    public void clearCart() {
        items.clear();
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void increaseQuantity(Long productId) {
        items.computeIfPresent(productId, (id, item) -> {
            item.setQuantity(item.getQuantity() + 1);
            return item;
        });
    }

    public void decreaseQuantity(Long productId) {
        items.computeIfPresent(productId, (id, item) -> {
            int newQuantity = item.getQuantity() - 1;
            if (newQuantity == 0) {
                return null;
            } else {
                item.setQuantity(newQuantity);
                return item;
            }
        });
    }
}
