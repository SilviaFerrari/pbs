package com.silviaferrari.pbs.service;

import com.silviaferrari.pbs.model.CartItem;
import com.silviaferrari.pbs.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public void removeProduct(Long productId) {
        items.remove(productId);
    }

    public void clearCart() {
        items.clear();
    }

    public double getTotal() {
        return items.values().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
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
