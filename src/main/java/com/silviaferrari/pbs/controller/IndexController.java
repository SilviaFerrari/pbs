package com.silviaferrari.pbs.controller;

import com.silviaferrari.pbs.model.Product;
import com.silviaferrari.pbs.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    /* indica a Spring di iniettare automaticamente un'istanza
    di una classe dove serve e si evita di creare nuovi oggetti con new
    Spring gestisce i componenti e li collega dovo c'è questa notazione */
    private final ProductRepository productRepository;

    public IndexController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println(">>> [HomeController] Caricamento della home page");
        List<Product> products = productRepository.findAll();  // prendi i prodotti
        model.addAttribute("products", products);  // passali alla vista
        return "index"; // renderizza index.html, non c'è bisogno di scrivere l'estensione
    }
}
