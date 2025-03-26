package com.example.cashcard;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CashCard {
    @Id
    private Long id;
    private Double amount;
    public CashCard() {
    }
    public CashCard(Long id, Double amount) {
        this.id = id;
        this.amount = amount;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashCard cashCard = (CashCard) o;
        return Objects.equals(id, cashCard.id) && Objects.equals(amount, cashCard.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}