package org.example;

class OrderService {
    enum Type {DELIVERY, PICKUP}

    final Type type;
    final String currency;
    final Long amount;

    OrderService(Type type,
                 String currency,
                 Long amount) {
        this.type = type;
        this.currency = currency;
        this.amount = amount;

    }

    public String getCurrency() {
        return currency;
    }

    public Long getAmount() {
        return amount;
    }

    Type getType() {
        return type;
    }
}