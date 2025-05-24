package com.silviaferrari.pbs.repository;

import com.silviaferrari.pbs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/* serve per permettere a Spring Data JPA di interagire
automaticamente con il database, senza scrivere a mano
le query SQL per fare delle operazioni */

public interface ProductRepository extends JpaRepository<Product, Long> {}

/* product è l'entità del database da gestire
* mentre long è il tipo della chiave primaria
* in questo modo tutte le operazioni necessarie
* alla gestione del database vengono generate */