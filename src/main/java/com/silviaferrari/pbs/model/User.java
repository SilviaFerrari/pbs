package com.silviaferrari.pbs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome utente è obbligatorio")
    @Size(min = 3, message = "Il nome utente deve contenere almeno 3 caratteri")
    private String username;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "La password deve contenere almeno una lettera e un numero")
    private String password;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
