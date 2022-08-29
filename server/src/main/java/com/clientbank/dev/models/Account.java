package com.clientbank.dev.models;

import com.clientbank.dev.enums.Currency;

import java.util.Objects;

public class Account {
    private Long id;
    private String number;
    private Currency currency;
    private Double balance;
    private Long customerId;

    public Account(Currency currency, Long customerId) {
        this.currency = currency;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customer) {
        this.customerId = customer.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id) && Objects.equals(number, account.number) && currency == account.currency && Objects.equals(balance, account.balance) && Objects.equals(customerId, account.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, currency, balance, customerId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", customer=" + customerId +
                '}';
    }
}
