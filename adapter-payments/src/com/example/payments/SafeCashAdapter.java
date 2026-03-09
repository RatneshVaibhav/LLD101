package com.example.payments;

import java.util.Objects;

/**
 * Adapter that wraps SafeCashClient and makes it compatible with the PaymentGateway interface.
 * Translates charge() into SafeCashClient's createPayment() + confirm() two-step API.
 */
public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "SafeCashClient must not be null");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId must not be null");
        // SafeCashClient expects (amount, user) ordering — note the argument flip
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}
