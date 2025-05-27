package com.silviaferrari.pbs.controller;

import com.silviaferrari.pbs.model.Product;
import com.silviaferrari.pbs.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        if (product == null) {
            return "redirect:/products"; // oppure una pagina di errore
        }
        model.addAttribute("product", product);
        System.out.println(">>> [Controller] Caricamento della pagina del prodotto.");
        return "product";
    }

    @GetMapping("/catalog")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        System.out.println(">>> [Controller] Caricamento del catalogo.");
        return "catalog";
    }
}
