package com.silviaferrari.pbs;

import com.silviaferrari.pbs.model.Product;
import com.silviaferrari.pbs.repository.ProductRepository;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class PbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PbsApplication.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean
    CommandLineRunner run(ProductRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Product("Classico", "Lievitato per eccellenza, come vuole la tradizione, con una cascata di uvetta e gustosi canditi. Anche con solo uvetta.", BigDecimal.valueOf(18), "/images/classico.jpg"));
                repo.save(new Product("Fragole e cioccolato", "Il perfetto connubio tra fragole e cioccolato crea un’armonia da favola.", BigDecimal.valueOf(20), "/images/fragole-cioccolato.jpg"));
                repo.save(new Product("Orzo e uvetta", "Impasto arricchito con pasta pura di orzo tostato, all’interno un’uvetta rimasta per due giorni a macerare nella birra.", BigDecimal.valueOf(22), "/images/orzo-uvetta.jpeg"));
                repo.save(new Product("Caffè e cioccolato", "Impasto con squisite pepite di cioccolato, arricchito con pasta pura di caffè.", BigDecimal.valueOf(20), "/images/caffè-cioccolato.jpg"));
                repo.save(new Product("Pingu", "Panettone al ketchup, tributo alle numerose richieste di Andrea durante i viaggi in autobus.", BigDecimal.valueOf(30), "/images/ketchup.webp"));
                repo.save(new Product("Vincent", "Cacciaviti: nulla di meglio per calmare un programmatore che debugga un codice.", BigDecimal.valueOf(25), "/images/cacciaviti.png"));
            }
        };
    }
}
