package com.silviaferrari.pbs.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PaymentForm {
    @NotBlank(message = "Il nome è obbligatorio")
    private String cardholderName;

    @NotBlank(message = "Il numero della carta è obbligatorio")
    @Pattern(regexp = "\\d{16}", message = "Numero carta non valido (16 cifre)")
    private String cardNumber;

    @NotBlank(message = "La data di scadenza è obbligatoria")
    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Formato MM/YY richiesto")
    private String expiryDate;

    @NotBlank(message = "Il CVV è obbligatorio")
    @Pattern(regexp = "\\d{3}", message = "CVV non valido (3 cifre)")
    private String cvv;

    public @NotBlank(message = "Il nome è obbligatorio") String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(@NotBlank(message = "Il nome è obbligatorio") String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public @NotBlank(message = "Il numero della carta è obbligatorio") @Pattern(regexp = "\\d{16}", message = "Numero carta non valido (16 cifre)") String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(@NotBlank(message = "Il numero della carta è obbligatorio") @Pattern(regexp = "\\d{16}", message = "Numero carta non valido (16 cifre)") String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public @NotBlank(message = "La data di scadenza è obbligatoria") @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Formato MM/YY richiesto") String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(@NotBlank(message = "La data di scadenza è obbligatoria") @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Formato MM/YY richiesto") String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public @NotBlank(message = "Il CVV è obbligatorio") @Pattern(regexp = "\\d{3}", message = "CVV non valido (3 cifre)") String getCvv() {
        return cvv;
    }

    public void setCvv(@NotBlank(message = "Il CVV è obbligatorio") @Pattern(regexp = "\\d{3}", message = "CVV non valido (3 cifre)") String cvv) {
        this.cvv = cvv;
    }
}