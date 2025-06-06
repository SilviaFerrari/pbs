package com.silviaferrari.pbs.controller;

import com.silviaferrari.pbs.model.CartItem;
import com.silviaferrari.pbs.model.Order;
import com.silviaferrari.pbs.model.PaymentForm;
import com.silviaferrari.pbs.model.User;
import com.silviaferrari.pbs.service.CartService;
import com.silviaferrari.pbs.service.EmailService;
import com.silviaferrari.pbs.service.OrderService;
import com.silviaferrari.pbs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;
    private final EmailService emailService;

    public OrderController(OrderService orderService, CartService cartService, UserService userService, EmailService emailService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/checkout")
    public String showCheckout(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", cartService.getTotal());
        return "checkout";
    }

    @PostMapping("/confirm")
    public String confirmOrder(Principal principal, RedirectAttributes redirectAttributes) {
        User user = userService.findByEmail(principal.getName());
        List<CartItem> cartItems = cartService.getCartItems();
        Order order = orderService.createOrder(user, cartItems);
        cartService.clearCart();
        redirectAttributes.addFlashAttribute("successMessage", "Ordine completato con successo!");
        return "redirect:/orders/payment/" + order.getId();
    }

    @GetMapping("/payment/{orderId}")
    public String showPaymentForm(@PathVariable Long orderId, Model model) {
        model.addAttribute("paymentForm", new PaymentForm());
        return "payment";
    }

    @PostMapping("/payment/{orderId}")
    public String processPayment(@PathVariable Long orderId,
            @Valid @ModelAttribute("paymentForm") PaymentForm paymentForm,
            BindingResult result,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Pagamento fallito!");
            return "payment";
        }

        User user = userService.findByEmail(principal.getName());
        emailService.sendPaymentReceipt(user.getEmail(), paymentForm.getCardholderName());

        redirectAttributes.addFlashAttribute("successMessage", "Pagamento avvenuto con successo! Ricevuta inviata via email.");
        return "redirect:/orders/thankyou/" + orderId;
    }

    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/";
    }
}