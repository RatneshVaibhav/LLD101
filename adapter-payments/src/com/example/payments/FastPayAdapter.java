package com.example.payments;

import java.util.Objects;

/**
 * Adapter that wraps FastPayClient and makes it compatible with the PaymentGateway interface.
 * The adapter translates the charge() call into FastPayClient's payNow() method signature.
 */
public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = Objects.requireNonNull(client, "FastPayClient must not be null");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId must not be null");
        // FastPayClient uses payNow(custId, amountCents)
        return client.payNow(customerId, amountCents);
    }
}
